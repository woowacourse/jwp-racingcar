package racingcar;

import racingcar.controller.RaceController;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RaceController raceController = new RaceController(
            new InputView(scanner),
            new OutputView(),
            new RandomNumberGenerator()
        );
        raceController.play();
        scanner.close();
    }
}
