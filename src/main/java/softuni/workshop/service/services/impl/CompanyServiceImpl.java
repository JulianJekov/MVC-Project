package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.Paths;
import softuni.workshop.data.dtos.Company.CompanyRootDto;
import softuni.workshop.data.entities.Company;
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
    private final ModelMapper modelMapper;


    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCompanies() throws JAXBException, FileNotFoundException {
        final String xml = Paths.COMPANIES_XML_PATH.toAbsolutePath().toString();

        final CompanyRootDto companyRootDto = xmlParser.convertFromFile(xml, CompanyRootDto.class);

        companyRootDto.getCompany()
                .stream()
                .map(c ->this.modelMapper.map(c, Company.class))
                .forEach(this.companyRepository::saveAndFlush);
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
