package racingcar.controller;

import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.GameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameController {

    private final RacingGameService service;

    public ConsoleRacingGameController(RacingGameService service) {
        this.service = service;
    }

    public void run() {
        OutputView.printBeforeRacing();
        String carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();

        CarGameRequest request = new CarGameRequest(carNames, tryCount);
        GameResponse response = service.play(request);
        OutputView.printResult(response);
    }
}
