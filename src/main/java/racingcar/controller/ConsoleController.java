package racingcar.controller;

import racingcar.game.Game;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    public void execute(final InputView inputView, final OutputView outputView) {
        Game game = makeGame(inputView);
        playGame(game, outputView);
    }

    public Game makeGame(InputView inputView) {
        inputView.showEnterCarNameMessage();
        final String carNames = inputView.getInputUntilExist();
        inputView.showEnterCountMessage();
        final String count = inputView.getInputUntilExist();
        return new Game(carNames, count);
    }

    public void playGame(final Game game, final OutputView outputView) {
        game.playGameWithoutPrint(new RandomNumberGenerator());
        outputView.printWinners(game.getWinners());
        outputView.printRoundResult(game.getCars());
    }
}
