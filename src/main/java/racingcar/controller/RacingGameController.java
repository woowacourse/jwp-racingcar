package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public <T> T retry(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retry(supplier);
        }
    }

    public void run() {
        RacingGame racingGame = initialize();
        play(racingGame);
        findWinners(racingGame);
        printResult(racingGame);
    }

    private RacingGame initialize() {
        List<String> carNames = retry(inputView::readCarNames);
        int count = retry(inputView::readCount);
        return new RacingGame(new RandomNumberGenerator(), carNames, count);
    }

    private void play(final RacingGame racingGame) {
        outputView.printResultMessage();
        racingGame.play();
    }

    private void findWinners(final RacingGame racingGame) {
        List<String> winners = racingGame.findWinners();
        outputView.printWinnersMessage(winners);
    }

    private void printResult(final RacingGame racingGame) {
        outputView.printCurrentCarPositions(racingGame.findCurrentCarPositions());
    }
}
