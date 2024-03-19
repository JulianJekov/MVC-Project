package softuni.workshop.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    @Column(nullable = false, length = 15)
    private String name;

    @OneToMany(mappedBy = "company", targetEntity = Project.class)
    private Set<Project> projects;
    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
