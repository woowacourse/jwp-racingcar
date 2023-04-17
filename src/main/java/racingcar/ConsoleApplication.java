package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public final class ConsoleApplication {

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            ConsoleController controller = new ConsoleController(
                    new InputView(scanner), new OutputView());
            controller.run(new DefaultMovingStrategy());
        }
    }
}
