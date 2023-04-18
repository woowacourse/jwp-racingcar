package racingcar.controller;

import java.util.List;
import racingcar.domain.CarFactory;
import racingcar.domain.RacingGame;
import racingcar.domain.Result;
import racingcar.dto.ResultDto;
import racingcar.strategy.MovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MovingStrategy movingStrategy;

    public RacingGameConsoleController(InputView inputView, OutputView outputView, MovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public void execute() {
        List<String> carNames = inputCarNames();
        int trialCount = inputTrialCount();
        RacingGame racingGame = new RacingGame(1, CarFactory.buildCars(carNames), movingStrategy);
        racingGame.race(trialCount);
        outputView.printResult(ResultDto.from(new Result(racingGame.winners(), racingGame.cars())));
    }

    private List<String> inputCarNames() {
        outputView.printInputCarNamesNotice();
        return inputView.inputCarNames();
    }

    private int inputTrialCount() {
        outputView.printInputTryTimesNotice();
        return inputView.inputTrialCount();
    }
}
