package softuni.workshop.data.dtos.Employee;

public class EmployeeViewDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String projectName;

    public EmployeeViewDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s%n\t" +
                "Age: %d%n\t" +
                "Project name: %s", this.firstName, this.lastName, this.age, this.projectName);
    }
}
