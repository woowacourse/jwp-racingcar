package racingcar.controller.console;

import org.springframework.stereotype.Controller;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.GameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Controller
public class ConsoleRacingGameController {
    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

        public void playGame() {
        String names = InputView.inputCarNames();
        int count = InputView.inputTryCount();

        CarGameRequest carGameRequest = new CarGameRequest(names, count);
        GameResponse gameResponse = racingGameService.play(carGameRequest);

        OutputView.printCarGameResult(gameResponse);
    }
}
