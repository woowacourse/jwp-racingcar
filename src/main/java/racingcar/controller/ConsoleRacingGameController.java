package racingcar.controller;

import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameController {
    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void play() {
        String names = InputView.inputCarNames();
        int count = InputView.inputTryCount();

        CarGameRequest carGameRequest = new CarGameRequest(names, count);
        CarGameResponse carGameResponse = racingGameService.play(carGameRequest);

        OutputView.printCarGameResult(carGameResponse);
    }
}
