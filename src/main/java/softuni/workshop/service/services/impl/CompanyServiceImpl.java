package softuni.workshop.service.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.dtos.CompanyRootDto;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public void importCompanies() {
        //TODO seed in database
    }

    @Override
    public boolean areImported() {

        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Paths.COMPANIES_XML_PATH);
    }
}
