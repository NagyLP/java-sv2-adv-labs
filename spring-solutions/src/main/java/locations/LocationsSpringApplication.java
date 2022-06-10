package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@SpringBootApplication
public class LocationsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsSpringApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("org.mariadb.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/locations")
//                .username("root")
//                .password("root555")
//                .build();
//    }

    // Validáció
//    @Bean
//    public ObjectMapper objectMapper(){
//        return new ObjectMapper().findAndRegisterModules();
//    }
}
