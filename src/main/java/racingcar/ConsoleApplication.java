package racingcar;

import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGame racingGame = new RacingGame(InputView.readCarNames());
        racingGame.moveCars(InputView.readCount());

        OutputView.printResultMessage();
        OutputView.printAllCars(racingGame.getCars());
        OutputView.printWinners(racingGame.decideWinners());
    }
}
