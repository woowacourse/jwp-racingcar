package racingcar.controller;

import java.util.List;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayResponseDto;
import racingcar.service.CarDtoBuilder;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int trialCount = inputView.askRacingCount();
        final RacingGame racingGame = RacingGame.of(carNames);

        racingGame.race(trialCount);

        PlayResponseDto playResult = new PlayResponseDto(
                CarDtoBuilder.responseDtos(racingGame.getCars()),
                String.join(", ", racingGame.getWinnerNames())
        );
        outputView.printPlayResult(playResult);
    }

}
