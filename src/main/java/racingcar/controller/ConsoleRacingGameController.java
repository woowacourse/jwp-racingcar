package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

import java.util.List;

public class ConsoleRacingGameController {

    private final InputView inputView;
    private final ConsoleView consoleView;
    private RacingGame racingGame;

    public ConsoleRacingGameController(InputView inputView, ConsoleView consoleView) {
        this.inputView = inputView;
        this.consoleView = consoleView;
    }

    public void start() {
        try {
            makeRacingGame(readCarNames(), readGameTry());
            startRacingGame();
            makeRacingGameResult();
        } catch (RuntimeException e) {
            consoleView.printExceptionMessage(e.getMessage());
            start();
        }
    }

    private List<String> readCarNames() {
        try {
            return inputView.readCarName();
        } catch (IllegalArgumentException e) {
            consoleView.printExceptionMessage(e.getMessage());
            return readCarNames();
        }
    }

    private int readGameTry() {
        try {
            return inputView.readGameTry();
        } catch (IllegalArgumentException e) {
            consoleView.printExceptionMessage(e.getMessage());
            return readGameTry();
        }
    }

    private void makeRacingGame(List<String> carNames, int gameTry) {
        this.racingGame = new RacingGame(carNames, gameTry, new RandomNumberGenerator());
    }

    private void startRacingGame() {
        consoleView.printGameResultMessage();
        while (racingGame.isGameOnGoing()) {
            racingGame.start();
        }
    }

    private void makeRacingGameResult() {
        consoleView.printRacingWinners(racingGame.getWinners());
        consoleView.printRacingStatus(racingGame.getCars());
    }
}
