package softuni.workshop.data.dtos.Company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto {

    @XmlElement(name = "company")
    private Set<CompanyImportDto> company;

    public CompanyRootDto() {
    }

    public Set<CompanyImportDto> getCompany() {
        return company;
    }

    public void setCompany(Set<CompanyImportDto> company) {
        this.company = company;
    }
}
