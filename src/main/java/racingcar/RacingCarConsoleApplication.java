package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.domain.RandomNumberGenerator;
import racingcar.repository.RacingCarConsoleRepository;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(
                inputView(),
                outputView(),
                new RacingCarService(new RacingCarConsoleRepository(), new RandomNumberGenerator())
        );
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
}
