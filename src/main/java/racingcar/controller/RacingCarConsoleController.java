package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.PositionOfCar;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(
            final InputView inputView,
            final OutputView outputView,
            final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        final RacingGame racingGame = generateGame();
        play(racingGame);
        findWinners(racingGame);
    }

    private RacingGame generateGame() {
        final Cars cars = retry(Cars::new, inputView::readCarNames);
        final Count count = retry(Count::new, inputView::readCount);
        return new RacingGame(new RandomNumberGenerator(), cars, count);
    }

    private <T, R> R retry(final Function<T, R> function, final Supplier<T> supplier) {
        try {
            return function.apply(supplier.get());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retry(function, supplier);
        }
    }

    private void play(final RacingGame racingGame) {
        outputView.printResultMessage();
        racingCarService.play(racingGame);
        final List<Car> cars = racingGame.getCurrentResult();
        outputView.printPosition(conversionPositionOfCars(cars));
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
