package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            RacingGameController controller = new RacingGameController(
                    new InputView(scanner), new OutputView());
            controller.run(new DefaultMovingStrategy());
        }
    }
}
