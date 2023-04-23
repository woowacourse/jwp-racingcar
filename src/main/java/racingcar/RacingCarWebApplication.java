package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.RacingGameConsoleController;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

@SpringBootApplication
public class RacingCarWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacingCarWebApplication.class, args);
        RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(RacingGameService.generateDefaultRacingGameService(), new InputView(new Parser(), new Scanner(System.in)), new OutputView());
        racingGameConsoleController.run();
    }

}
