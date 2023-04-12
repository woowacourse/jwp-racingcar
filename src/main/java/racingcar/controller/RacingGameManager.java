package racingcar.controller;

import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameManager {
    private RacingGame racingGame;

    public void run() {
        List<Name> carNames = readCarNames();
        TryCount tryCount = readTryCount();
        racingGame = new RacingGame(carNames);

        startRace(tryCount);

        OutputView.printAllCars(racingGame.getCars());
        OutputView.printWinners(racingGame.decideWinners());
    }

    private void startRace(TryCount tryCount) {
        OutputView.printResultMessage();

        for (int i = 0; i < tryCount.getCount(); i++) {
            racingGame.moveCars(new TryCount(1));
            OutputView.printAllCars(racingGame.getCars());
        }
    }

    private TryCount readTryCount() {
        try {
            return new TryCount(InputView.readCount());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return readTryCount();
        }
    }

    private List<Name> readCarNames() {
        try {
            return InputView.readCarNames()
                    .stream()
                    .map(Name::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return readCarNames();
        }
    }
}
