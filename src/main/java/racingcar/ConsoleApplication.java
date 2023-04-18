package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleController;
import racingcar.dao.ConsoleCarDao;
import racingcar.dao.ConsoleGameDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    private static InputView createInputView() {
        return new InputView(new Scanner(System.in), new OutputView());
    }

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                createInputView(),
                new OutputView(),
                new RacingCarService(new ConsoleGameDao(), new ConsoleCarDao())
        );
        consoleController.run();
    }
}
