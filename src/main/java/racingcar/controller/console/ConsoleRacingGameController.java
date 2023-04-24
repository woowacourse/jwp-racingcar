package racingcar.controller.console;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public class ConsoleRacingGameController {
    public void playGame() {
        RacingGame racingGame = requestRacingGame();
        racingGame.play();
        OutputView.printWinners(racingGame.getWinnerNames());
        OutputView.printCarResults(racingGame.getCars());
    }

    private RacingGame requestRacingGame() {
        Cars cars = generateUntilCorrectInput(InputView::inputCarNames, Cars::fromNameValues);
        TryCount tryCount = generateUntilCorrectInput(InputView::inputTryCount, TryCount::new);
        return new RacingGame(new RandomNumberGenerator(), cars, tryCount);
    }

    private <T, E> E generateUntilCorrectInput(Supplier<T> inputAction, Function<T, E> generator) {
        try {
            return generator.apply(inputAction.get());
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return generateUntilCorrectInput(inputAction, generator);
        }
    }
}
