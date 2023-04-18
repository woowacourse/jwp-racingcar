package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.RacingCarNames;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayResponseDtoConverter;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final RacingGame racingGame = RacingGame.of(new RacingCarNames(carNames).createCars());
        racingGame.play(racingCount);
        final List<Car> racingCars = racingGame.racingCars();
        final List<String> winningCarNames = racingGame.findWinningCarNames();

        outputView.printPlayResult(PlayResponseDtoConverter.from(racingCars, winningCarNames));
    }

}
