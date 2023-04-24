package racingcar.controller;

import org.springframework.stereotype.Component;
import racingcar.controller.dto.GameResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.MessageView;
import racingcar.view.OutputView;

@Component
public class ConsoleController {

    private final RacingCarService racingCarService;

    public ConsoleController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void runGame() {
        GameResultDto gameResultDto = racingCarService.runGame(getCarNames(), getTryCount());
        OutputView.printResult(gameResultDto);
    }

    private String getCarNames() {
        MessageView.printCarNameMessage();

        try {
            return InputView.inputCarNames();
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            return getCarNames();
        }
    }

    private int getTryCount() {
        MessageView.printTryCountMessage();

        try {
            return InputView.inputTryCount();
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            return getTryCount();
        }
    }
}
