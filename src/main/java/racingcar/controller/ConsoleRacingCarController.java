package racingcar.controller;

import java.util.List;

import racingcar.controller.dto.RacingCarGameResponse;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.RacingCarResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    public void run(final NumberGenerator numberGenerator) {
        try {
            final List<String> names = List.of(InputView.readCarNames().split(","));
            final int attempt = InputView.readAttemptNumber();
            final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
            racingCarGame.play();
            final RacingCarResult racingCarResult = racingCarGame.getResult();
            final RacingCarGameResponse racingCarGameResponse = RacingCarGameResponse.from(racingCarResult);
            OutputView.printResult(racingCarGameResponse);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            run(numberGenerator);
        }
    }
}
