package softuni.workshop.data.dtos.Project;

import java.math.BigDecimal;

public class ProjectViewDto {

    private String name;

    private String description;

    private BigDecimal payment;

    public ProjectViewDto() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return String.format("Project Name: %s%n\t" +
                "Description: %s%n\t" +
                "%.2f", this.name, this.description, this.payment);
    }
}
