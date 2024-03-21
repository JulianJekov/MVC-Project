package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.dtos.Employee.EmployeeImportDto;
import softuni.workshop.data.dtos.Employee.EmployeeRootDto;
import softuni.workshop.data.dtos.Employee.EmployeeViewDto;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, XmlParser xmlParser, ModelMapper modelMapper, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public void importEmployees() throws JAXBException, FileNotFoundException {
        String xml = Paths.EMPLOYEES_XML_PATH.toAbsolutePath().toString();

        EmployeeRootDto employeeRootDto = xmlParser.convertFromFile(xml, EmployeeRootDto.class);

        for (EmployeeImportDto employeeImportDto : employeeRootDto.getEmployees()) {
            Employee employee = modelMapper.map(employeeImportDto, Employee.class);
            employee.setProject(this.projectRepository.findByName(employeeImportDto.getProject().getName()));

            this.employeeRepository.saveAndFlush(employee);
        }
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {

        return Files.readString(Paths.EMPLOYEES_XML_PATH);
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        
        return this.employeeRepository.findByAgeGreaterThan(25)
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewDto.class))
                .map(EmployeeViewDto::toString)
                .collect(Collectors.joining("\n"));
    }
}
