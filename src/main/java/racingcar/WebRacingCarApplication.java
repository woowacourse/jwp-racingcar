package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@SpringBootApplication
public class WebRacingCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebRacingCarApplication.class, args);
    }
}
