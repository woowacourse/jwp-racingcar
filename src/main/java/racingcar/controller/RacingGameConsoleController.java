package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {


    public void run() {
        List<String> names = InputView.inputNames();
        int count = InputView.inputTryCount();

        List<Car> cars = names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        RacingGame racingGame = new RacingGame(count, new Cars(cars));
        racingGame.run();

        OutputView.printRacing(racingGame.getCars());
        OutputView.printWinners(racingGame.findWinners());
    }
}
