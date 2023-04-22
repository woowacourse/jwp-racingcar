package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import racingcar.controller.ConsoleGameController;

@SpringBootApplication
public class RacingCarApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RacingCarApplication.class, args);
        ConsoleGameController consoleGameController = applicationContext.getBean(
                "consoleGameController",
                ConsoleGameController.class
        );
        consoleGameController.run();
    }
}
