package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.Names;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.MovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;


    public ConsoleController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(final MovingStrategy movingStrategy) {
        final Names carNames = new Names(inputView.readCarNames());
        final TryCount tryCount = new TryCount(inputView.readTryCount());
        final RacingGame racingGame = new RacingGame(carNames, tryCount);
        final Cars finalStatus = racingGame.run(movingStrategy);
        outputView.printTotalMovingStatus(finalStatus);
        outputView.printWinners(racingGame.getWinners());
    }
}
