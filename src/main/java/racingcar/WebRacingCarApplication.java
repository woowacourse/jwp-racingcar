package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import racingcar.controller.ConsoleRacingCarController;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {ConsoleRacingCarController.class, ConsoleRacingCarApplication.class}
))
public class WebRacingCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebRacingCarApplication.class, args);
    }
}
