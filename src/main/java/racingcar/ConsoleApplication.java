package racingcar;

import racingcar.controller.RacingConsoleController;
import racingcar.exception.CustomException;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;
import racingcar.view.inputview.InputView;
import racingcar.view.inputview.KoreanInputView;
import racingcar.view.outputview.KoreanOutputView;
import racingcar.view.outputview.OutputView;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new KoreanInputView(scanner);
        OutputView outputView = new KoreanOutputView();
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
