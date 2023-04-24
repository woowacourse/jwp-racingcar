package racingcar.controller;

import java.util.List;

import racingcar.controller.dto.RacingCarGameResponse;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.RacingCarResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    public void run(final NumberGenerator numberGenerator) {
        try {
            final List<String> names = List.of(InputView.readCarNames().split(","));
            final int attempt = InputView.readAttemptNumber();
            final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
            racingCarGame.play();
            final RacingCarResultDto racingCarResultDto = racingCarGame.getResult();
            final RacingCarGameResponse racingCarGameResponse = RacingCarGameResponse.from(racingCarResultDto);
            OutputView.printResult(racingCarGameResponse);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            run(numberGenerator);
        }
    }
}
