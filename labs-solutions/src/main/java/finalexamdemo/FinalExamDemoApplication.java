package finalexamdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalExamDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExamDemoApplication.class, args);
    }


    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
