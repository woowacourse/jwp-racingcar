package racingcar.controller;

import racingcar.model.MoveCount;
import racingcar.model.RacingGame;
import racingcar.model.car.Cars;
import racingcar.model.manager.CarMoveManager;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CarMoveManager carMoveManager;

    public ConsoleController(InputView inputView, OutputView outputView, CarMoveManager carMoveManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carMoveManager = carMoveManager;
    }

    public void play() {
        RacingGame racingGame = createRacingGame();
        racingGame.play();
        showGameResult(racingGame);
    }

    private RacingGame createRacingGame() {
        Cars cars = Cars.from(inputView.readCarNames());
        MoveCount moveCount = new MoveCount(inputView.readMoveCount());
        return new RacingGame(carMoveManager, cars, moveCount);
    }

    private void showGameResult(RacingGame racingGame) {
        outputView.printResultMessage();
        outputView.printWinners(racingGame.getWinners());
        outputView.printResult(racingGame.getCars());
    }

}
