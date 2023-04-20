package racingcar.controller;

import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingResultResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class ConsoleRacingCarController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public ConsoleRacingCarController(InputView inputView, OutputView outputView, RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        String carNames = inputView.readCarNames();
        int round = inputView.readRacingRound();
        RacingStartRequest racingStartRequest = new RacingStartRequest(carNames, round);

        RacingResultResponse racingResultResponse = racingCarService.play(racingStartRequest);
        outputView.printResultMessage();
        outputView.printFinalResult(racingResultResponse.getWinners());
        outputView.printDistanceResult(racingResultResponse.getRacingCars());
    }
}
