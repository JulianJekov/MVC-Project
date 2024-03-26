package softuni.workshop.config;

import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.workshop.data.dtos.Employee.EmployeeViewDto;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.ValidatorUtilImpl;
import softuni.workshop.util.XmlParser;
import softuni.workshop.util.XmlParserImpl;

import javax.print.attribute.standard.Destination;
import java.util.Objects;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }
}
