package racingcar;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RacingCarConsoleApplication {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(RacingCarConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
