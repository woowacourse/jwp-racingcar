package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    @Autowired
    public ConsoleController(RacingCarService racingCarService) {
        this.inputView = new InputView(System.in);
        this.outputView = new OutputView();
        this.racingCarService = racingCarService;
    }

    public void plays() {
        try {
            GameRequestDtoForPlays gameRequestDtoForPlays = new GameRequestDtoForPlays(
                    inputView.requestCarNames(),
                    inputView.requestNumberOfTimes()
            );

            GameResponseDto gameResponseDto = racingCarService.plays(gameRequestDtoForPlays);
            outputView.printResult(gameResponseDto);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
    }

}
