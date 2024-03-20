package softuni.workshop.service.services;

import java.io.IOException;

public interface ProjectService {

    void importProjects();

    boolean areImported();

    String readProjectsXmlFile() throws IOException;

    String exportFinishedProjects();
}
