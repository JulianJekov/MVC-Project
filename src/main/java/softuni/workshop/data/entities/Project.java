package softuni.workshop.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;



@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(nullable = false)
    private BigDecimal payment;

    @Column(name = "start_date")
    private String startDate;

    @ManyToOne(optional = false)
    private Company company;

    public Project() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
