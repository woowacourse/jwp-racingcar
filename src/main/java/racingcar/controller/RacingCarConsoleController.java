package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

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
        RacingGameResponseDto racingGameResponseDto = racingCarService.play(racingGame);
        outputView.printResult(racingGameResponseDto);
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
}
