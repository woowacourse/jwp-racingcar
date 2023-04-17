package racingcar.controller;

import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

//        final List<JudgedCarDto> judgedCars = new RacingGame().play(racingCount, carNames);
//
//        outputView.printPlayResult(PlayResponseDtoConverter.from(judgedCars));
    }

}
