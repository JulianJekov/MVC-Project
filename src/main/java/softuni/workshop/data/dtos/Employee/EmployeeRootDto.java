package softuni.workshop.data.dtos.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootDto {

    @XmlElement(name = "employee")
    private Set<EmployeeImportDto> employees;

    public EmployeeRootDto() {
    }

    public Set<EmployeeImportDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeImportDto> employees) {
        this.employees = employees;
    }
}
