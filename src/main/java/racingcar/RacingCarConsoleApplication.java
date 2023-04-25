package racingcar;

import java.util.Scanner;

import racingcar.controller.RacingCarConsoleController;
import racingcar.database.InMemoryDatabase;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(inputView(),
                outputView(), racingCarService());
        racingCarConsoleController.run();
    }

    private static InputView inputView() {
        return new InputView(scanner());
    }

    private static Scanner scanner() {
        return new Scanner(System.in);
    }

    private static OutputView outputView() {
        return new OutputView();
    }

    private static RacingCarService racingCarService() {
        return new RacingCarService(new InMemoryDatabase());
    }

}
