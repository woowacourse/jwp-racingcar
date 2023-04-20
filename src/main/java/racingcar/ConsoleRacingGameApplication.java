package racingcar;

import racingcar.presentation.ConsoleRacingGameController;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

import java.util.Scanner;

public class ConsoleRacingGameApplication {

    public static void main(String[] args) {
        new ConsoleRacingGameController(
                new InputView(new Scanner(System.in)),
                new ConsoleView()
        ).start();
    }
}
