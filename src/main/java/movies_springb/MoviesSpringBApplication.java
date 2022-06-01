package movies_springb;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviesSpringBApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesSpringBApplication.class, args);
    }


    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper createModelMapper = new ModelMapper();
        createModelMapper.getConfiguration().setMatchingStrategy(
                MatchingStrategies.STRICT);
        return createModelMapper;
    }
}
