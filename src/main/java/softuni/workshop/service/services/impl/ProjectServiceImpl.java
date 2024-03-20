package softuni.workshop.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.ProjectService;

import java.io.IOException;
import java.nio.file.Files;


@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void importProjects(){
        //TODO seed in database
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
    public String exportFinishedProjects(){
        //TODO export finished projects
        return null;
    }
}
