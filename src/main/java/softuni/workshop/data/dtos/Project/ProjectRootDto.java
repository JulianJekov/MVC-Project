package softuni.workshop.data.dtos.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootDto {

    @XmlElement(name = "project")
    private Set<ProjectImportDto> projects;

    public ProjectRootDto() {
    }

    public Set<ProjectImportDto> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectImportDto> projects) {
        this.projects = projects;
    }
}
