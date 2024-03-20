package softuni.workshop.service.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyService {

    void importCompanies();

    boolean areImported();

    String readCompaniesXmlFile() throws JAXBException, IOException;
}
