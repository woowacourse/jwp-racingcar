package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameMoveStrategy;
import racingcar.dto.RacingGameResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleRacingGameController {

    public void run() {
        RacingGame game = initializeGame();
        while (!game.isEnd()) {
            game.playOneRound();
        }
        showResult(game);
    }

    private RacingGame initializeGame() {
        List<String> names = InputView.inputNames();
        Cars cars = new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        int tryCount = InputView.inputTryCount();

        return new RacingGame(new RacingGameMoveStrategy(), tryCount, cars);
    }

    private void showResult(RacingGame game) {
        Cars cars = game.getCars();
        List<Car> winners = cars.findWinners();
        RacingGameResponse response = RacingGameResponse.of(winners, cars);
        OutputView.printWinners(response.getWinners());
        OutputView.printRacing(response.getRacingCars());
    }
}
