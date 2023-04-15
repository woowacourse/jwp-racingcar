package racingcar.controller;

import java.util.List;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDtoConverter;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RacingGame racingGame = new RacingGame();

    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int trialCount = inputView.askRacingCount();

        final List<CarDto> racedCars = racingGame.play(trialCount, carNames);

        outputView.printPlayResult(PlayResponseDtoConverter.of(racedCars));
    }

}
