package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleRacingCarController;
import racingcar.dao.ConsoleCarDao;
import racingcar.dao.ConsoleGameDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    private static InputView createInputView() {
        return new InputView(new Scanner(System.in), new OutputView());
    }

    public static void main(String[] args) {
        ConsoleRacingCarController consoleRacingCarController = new ConsoleRacingCarController(
                createInputView(),
                new OutputView(),
                new RacingCarService(new ConsoleGameDao(), new ConsoleCarDao())
        );
        consoleRacingCarController.run();
    }
}
