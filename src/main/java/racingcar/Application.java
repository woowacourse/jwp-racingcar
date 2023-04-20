package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.LocalCarDao;
import racingcar.dao.LocalGameDao;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class Application {

    private static InputView createInputView() {
        return new InputView(new Scanner(System.in), new OutputView());
    }

    private static OutputView createOutputView() {
        return new OutputView();
    }

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                createInputView(),
                createOutputView(),
                new RacingCarService(new LocalGameDao(), new LocalCarDao(), new RandomSingleDigitGenerator()));
        consoleController.createGameAndPlay();
    }
}
