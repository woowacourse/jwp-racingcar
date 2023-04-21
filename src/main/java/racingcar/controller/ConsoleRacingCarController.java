package racingcar.controller;

import java.io.IOException;
import java.util.List;

import racingcar.controller.dto.RacingCarGameResponse;
import racingcar.service.MainRacingCarService;
import racingcar.service.dto.RacingCarResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final MainRacingCarService mainRacingCarService;

    public ConsoleRacingCarController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    public void run() {
        try {
            final List<String> names = List.of(InputView.readCarNames().split(","));
            final int attempt = InputView.readAttemptNumber();
            final RacingCarResult racingCarResult = mainRacingCarService.raceCar(names, attempt);
            final RacingCarGameResponse racingCarGameResponse = RacingCarGameResponse.from(racingCarResult);
            OutputView.printResult(racingCarGameResponse);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
}
