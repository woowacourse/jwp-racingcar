package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@SpringBootApplication
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = ASSIGNABLE_TYPE, classes = ConsoleRacingCarApplication.class)
)
public class WebRacingCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebRacingCarApplication.class, args);
    }
}
