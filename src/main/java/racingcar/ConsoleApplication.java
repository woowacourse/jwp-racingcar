package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

import java.util.Scanner;

public class ConsoleApplication {

    public static void main(String[] args) {
        new ConsoleRacingGameController(
                new InputView(new Scanner(System.in)),
                new ConsoleView()
        ).start();
    }
}
