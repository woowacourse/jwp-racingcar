package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleRacingCarController;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        ConsoleRacingCarController controller = new ConsoleRacingCarController(new RandomNumberGenerator(),
                new InputView(new Scanner(System.in)),
                new OutputView());
        controller.run();
    }
}
