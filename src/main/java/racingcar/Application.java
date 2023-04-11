package racingcar;

import racingcar.domain.RacingGame;
import racingcar.utils.powerGenerator.RandomPowerGenerator;
import racingcar.view.InputView;
import racingcar.view.ResultView;

public class Application {
    public static void main(String[] args) {
        final RandomPowerGenerator randomPowerGenerator = new RandomPowerGenerator();
        final String[] carNames = InputView.getCarNames();
        final int tryCount = InputView.getTryCount();
        System.out.println();

        RacingGame racingGame = new RacingGame(carNames, tryCount, randomPowerGenerator);
        racingGame.start();
        ResultView.printWinners(racingGame.getWinners());
    }
}
