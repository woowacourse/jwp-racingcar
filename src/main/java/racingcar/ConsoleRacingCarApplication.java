package racingcar;

import java.util.List;

import racingcar.controller.dto.RacingCarGameResponse;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCarGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.dto.RacingCarResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final var numberGenerator = new RandomNumberGenerator();
        run(numberGenerator);
    }

    private static void run(final NumberGenerator numberGenerator) {
        try {
            // input
            final var names = List.of(InputView.readCarNames().split(","));
            final var attempt = InputView.readAttemptNumber();

            // process
            final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
            racingCarGame.play();

            // output
            final RacingCarResultDto racingCarResultDto = racingCarGame.getResult();
            final RacingCarGameResponse result = RacingCarGameResponse.from(racingCarResultDto);
            OutputView.printResult(result);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            run(numberGenerator);
        }
    }
}
