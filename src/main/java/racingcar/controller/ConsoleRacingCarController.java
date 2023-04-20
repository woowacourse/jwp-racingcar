package racingcar.controller;

import java.io.IOException;

import racingcar.dto.NamesAndCountDto;
import racingcar.dto.WinnersAndCarsDto;
import racingcar.service.MainRacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final MainRacingCarService mainRacingCarService;

    public ConsoleRacingCarController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    public void run() {
        try {
            String names = InputView.readCarNames();
            int attempt = InputView.readAttemptNumber();
            NamesAndCountDto namesAndCountDto = new NamesAndCountDto(names, attempt);
            WinnersAndCarsDto winnersAndCarsDto = mainRacingCarService.raceCar(namesAndCountDto);
            OutputView.printResult(winnersAndCarsDto);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
}
