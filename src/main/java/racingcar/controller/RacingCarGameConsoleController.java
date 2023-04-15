package racingcar.controller;

import racingcar.domain.car.Car;
import racingcar.domain.game.RacingCarGame;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.NumberMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RacingCarGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MovingStrategy movingStrategy;

    public RacingCarGameConsoleController(InputView inputView, OutputView outputView, NumberMovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public void run() {
        final RacingCarGame racingCarGame = createNewGame();
        race(racingCarGame);
        printResult(racingCarGame);
    }

    private RacingCarGame createNewGame() {
        final String carNames = repeatUntilNoIAE(inputView::readCarNames);
        return RacingCarGame.createNewGame(carNames);
    }

    private void race(final RacingCarGame racingCarGame) {
        int count = repeatUntilNoIAE(inputView::readTryCount);
        racingCarGame.moveCars(count, movingStrategy);
    }

    private void printResult(final RacingCarGame racingCarGame) {
        final Map<String, Integer> raceResult = racingCarGame.getCars()
                                                             .stream()
                                                             .collect(Collectors.toUnmodifiableMap(Car::getName, Car::getPosition));
        final List<String> winners = racingCarGame.getWinners()
                                                  .stream()
                                                  .map(Car::getName)
                                                  .collect(Collectors.toUnmodifiableList());

        outputView.printResultOpeningMessage();
        outputView.printRaceResult(raceResult);
        outputView.printWinners(winners);
    }

    private <T> T repeatUntilNoIAE(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilNoIAE(supplier);
        }
    }
}
