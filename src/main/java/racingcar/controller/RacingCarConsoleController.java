package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.PositionOfCar;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarConsoleController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final RacingGame racingGame = generateGame();
        play(racingGame);
        findWinners(racingGame);
    }

    private RacingGame generateGame() {
        while (true) {
            try {
                final List<String> carNames = inputView.readCarNames();
                final int count = inputView.readCount();
                return new RacingGame(new RandomNumberGenerator(), new Cars(createCars(carNames)), new Count(count));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Car> createCars(final List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void play(final RacingGame racingGame) {
        outputView.printResultMessage();
        while (racingGame.isPlayable()) {
            racingGame.play();
            final List<Car> cars = racingGame.getCurrentResult();
            outputView.printPosition(conversionPositionOfCars(cars));
        }
    }

    private List<PositionOfCar> conversionPositionOfCars(final List<Car> cars) {
        return cars.stream()
                .map(PositionOfCar::from)
                .collect(Collectors.toList());
    }


    private void findWinners(final RacingGame racingGame) {
        final List<String> winners = racingGame.findWinners();
        outputView.printWinnersMessage(winners);
    }
}
