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
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        MovingStrategy movingStrategy = new RandomMovingStrategy();

        RacingConsoleController racingConsoleController
                = new RacingConsoleController(inputView, outputView, movingStrategy);

        try {
            racingConsoleController.start();
        } catch (CustomException customException) {
            racingConsoleController.terminated(customException);
        }
    }
}
