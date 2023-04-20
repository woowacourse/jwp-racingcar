package racingcar.controller;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.GameRound;
import racingcar.domain.NumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void newCarNames() {
        boolean hasException = true;
        while (hasException) {
            hasException = newCarNamesHasException();
        }
    }

    public void newGameRound() {
        boolean hasException = true;
        while (hasException) {
            hasException = newGameRoundHasException();
        }
    }

//    public void play() {
//        outputView.printResultMessage();
//        while (!gameManager.isEnd()) {
//            List<CarStatusResponse> roundResultCarStatus = gameManager.playGameRound();
//            outputView.printRoundResult(roundResultCarStatus);
//        }
//        GameResultResponse winnerNames = gameManager.decideWinner();
//        outputView.printEndGameResult(winnerNames);
//    }

    private boolean newCarNamesHasException() {
        try {
            final List<String> inputCarNames = inputView.inputCarName();
            Cars.from(inputCarNames);
            return false;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }

    private boolean newGameRoundHasException() {
        try {
            final int inputGameRound = inputView.inputGameRound();
            new GameRound(inputGameRound);
            return false;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }
}
