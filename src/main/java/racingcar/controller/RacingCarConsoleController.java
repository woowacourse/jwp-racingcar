package racingcar.controller;

import java.util.List;
import racingcar.domain.RacingGame;
import racingcar.dto.JudgedCarDto;
import racingcar.dto.PlayResponseDtoConverter;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final List<JudgedCarDto> judgedCars = new RacingGame().play(racingCount, carNames);

        outputView.printPlayResult(PlayResponseDtoConverter.from(judgedCars));
    }

}
