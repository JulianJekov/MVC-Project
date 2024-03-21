package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.dtos.Project.ProjectImportDto;
import softuni.workshop.data.dtos.Project.ProjectRootDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;


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
        String xml = Paths.PROJECTS_XML_PATH.toAbsolutePath().toString();
        ProjectRootDto projectRootDto = xmlParser.convertFromFile(xml, ProjectRootDto.class);
        System.out.println();

        for (ProjectImportDto projectImportDto : projectRootDto.getProjects()) {
            Project project = modelMapper.map(projectImportDto, Project.class);
            project.setCompany(this.companyRepository.findByName(projectImportDto.getCompanyName().getName()));
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

    private Company setCompany(ProjectImportDto dto) {
        return this.companyRepository.findByName(dto.getCompanyName().getName());
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
        //TODO export finished projects
        return null;
    }
}
