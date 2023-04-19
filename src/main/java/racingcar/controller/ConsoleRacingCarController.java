package racingcar.controller;

import java.io.IOException;

import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
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
            RequestDto requestDto = new RequestDto(names, attempt);
            ResponseDto responseDto = mainRacingCarService.raceCar(requestDto);
            OutputView.printResult(responseDto);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
}
