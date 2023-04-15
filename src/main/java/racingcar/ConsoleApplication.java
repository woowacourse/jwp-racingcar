package racingcar;

import racingcar.controller.RacingCarGameConsoleController;
import racingcar.domain.strategy.NumberMovingStrategy;
import racingcar.domain.strategy.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            RacingCarGameConsoleController consoleController = new RacingCarGameConsoleController(new InputView(scanner),
                    new OutputView(),
                    new NumberMovingStrategy(new RandomNumberGenerator()));

            consoleController.run();
        }
    }
}
