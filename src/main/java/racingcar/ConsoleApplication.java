package racingcar;

import racingcar.controller.RacingConsoleController;
import racingcar.exception.CustomException;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final MovingStrategy movingStrategy = new RandomMovingStrategy();

        final RacingConsoleController racingConsoleController
                = new RacingConsoleController(inputView, outputView, movingStrategy);

        try {
            racingConsoleController.start();
        } catch (CustomException customException) {
            racingConsoleController.terminated(customException);
        }
    }
}
