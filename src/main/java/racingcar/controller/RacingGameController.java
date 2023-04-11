package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {

    public void run() {
        List<String> names = InputView.inputNames();
        Cars cars = new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        int tryCount = InputView.inputTryCount();

        RacingGame game = new RacingGame(tryCount, cars);
        while (!game.isEnd()) {
            game.playOneRound();
            OutputView.printRacing(cars);
        }
        List<String> winnerNames = game.getWinnerNames();
        OutputView.printWinners(winnerNames);
    }
}
