package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto {

    @XmlElement(name = "company")
    private CompanyImportDto company;

    public CompanyRootDto() {
    }

    public CompanyImportDto getCompany() {
        return company;
    }

    public void setCompany(CompanyImportDto company) {
        this.company = company;
    }
}
