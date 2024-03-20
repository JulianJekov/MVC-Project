package softuni.workshop.service.services;

import java.io.IOException;

public interface EmployeeService {

    void importEmployees();

    boolean areImported();

    String readEmployeesXmlFile() throws IOException;

    String exportEmployeesWithAgeAbove();
}
