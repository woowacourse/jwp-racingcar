package racingcar.controller;

import java.util.List;
import racingcar.domain.CarFactory;
import racingcar.domain.RacingGame;
import racingcar.domain.Result;
import racingcar.dto.ResultDto;
import racingcar.strategy.MovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {

    private final InputView inputView;

    private final OutputView outputView;

    private final MovingStrategy movingStrategy;

    public RacingGameController(InputView inputView, OutputView outputView,
        MovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public void execute() {
        List<String> carNames = getCarNames();
        int tryTimes = getTryTimes();
        RacingGame racingGame = new RacingGame(1, CarFactory.buildCars(carNames), movingStrategy);
        racingGame.race(tryTimes);
        outputView.printResult(ResultDto.from(new Result(racingGame.getWinners(), racingGame.getCars())));
    }

    private List<String> getCarNames() {
        outputView.printInputCarNamesNotice();
        return inputView.inputCarNames();
    }

    private int getTryTimes() {
        outputView.printInputTryTimesNotice();
        return inputView.inputTryTimes();
    }
}
