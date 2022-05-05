package locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LocationsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsSpringApplication.class, args);
    }

    @Bean
    public LocationsService locationsService() {
        return new LocationsService(new ArrayList<>(List.of(new Location(
                1L, "TesztHejj", 2, 3))));
    }

}
