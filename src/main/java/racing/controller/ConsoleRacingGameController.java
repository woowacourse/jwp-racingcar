package racing.controller;

import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.GameInfoResponse;
import racing.domain.Cars;
import racing.ui.input.InputView;
import racing.service.RacingGameService;
import racing.ui.output.OutputView;

public class ConsoleRacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController() {
        this.racingGameService = new RacingGameService();
    }

    public void start() {
        OutputView.printPhrase();
        RacingGameInfoRequest request = new RacingGameInfoRequest(InputView.inputCarsName(), Integer.parseInt(InputView.inputCount()));
        Cars cars = racingGameService.executeConsoleGame(request);
        OutputView.printResult(cars);
    }

}
