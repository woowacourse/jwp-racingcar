package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.GameManager;
import racingcar.domain.GameRound;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final GameManager gameManager = initialize();
        final ResultResponse resultResponse = new ResultResponse(gameManager.decideWinner(),
                gameManager.getResultCars());
        outputView.printEndGameResult(resultResponse);
    }

    private GameManager initialize() {
        final NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest(inputView.inputCarName(),
                inputView.inputGameRound());
        final Cars cars = Cars.from(namesAndCountRequest.getNames());
        final GameRound gameRound = new GameRound(namesAndCountRequest.getCount());
        return new GameManager(cars, gameRound, new RandomNumberGenerator());
    }
}
