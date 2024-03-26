package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.dtos.Project.ProjectImportDto;
import softuni.workshop.data.dtos.Project.ProjectRootDto;
import softuni.workshop.data.dtos.Project.ProjectViewDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;


    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, XmlParser xmlParser, ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public void importProjects() throws IOException, JAXBException {
        final String xml = Paths.PROJECTS_XML_PATH.toAbsolutePath().toString();
        final ProjectRootDto projectRootDto = xmlParser.convertFromFile(xml, ProjectRootDto.class);

        for (ProjectImportDto projectImportDto : projectRootDto.getProjects()) {
            final Project project = modelMapper.map(projectImportDto, Project.class);
            final Company company = this.companyRepository.findByName(projectImportDto.getCompanyName().getName());
            project.setCompany(company);
            this.projectRepository.saveAndFlush(project);
        }

//        projectRootDto.getProjects()
//                .stream()
//                .map(p -> {
//                    Project project = modelMapper.map(p, Project.class);
//                    project.setCompany(this.companyRepository.findByName(p.getCompanyName().getName()));
//                    return project;
//                })
//                .forEach(this.projectRepository::saveAndFlush);
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return Files.readString(Paths.PROJECTS_XML_PATH);
    }

    @Override
    public String exportFinishedProjects() {
        return this.projectRepository.findByIsFinishedTrue()
                .stream()
                .map(p -> modelMapper.map(p, ProjectViewDto.class))
                .map(ProjectViewDto::toString)
                .collect(Collectors.joining("\n"));
    }
}