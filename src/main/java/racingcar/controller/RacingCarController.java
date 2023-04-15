package racingcar.controller;

import java.util.List;
import racingcar.dto.RacingCarDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void run() {
        List<String> carNames = requestCarNames();
        int roundCount = requestRoundCount();
        int gameId = racingCarService.playRacingGame(carNames, roundCount);
        String winners = racingCarService.findWinners(gameId);
        List<RacingCarDto> racingCars = racingCarService.findRacingCars(gameId);
        OutputView.printRoundState(racingCars);
        OutputView.printRacingResult(winners);
    }

    private List<String> requestCarNames() {
        try {
            return inputCarNames();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return requestCarNames();
        }
    }

    private List<String> inputCarNames() {
        OutputView.printCarNameRequestMsg();
        return InputView.readCarNames();
    }

    private int requestRoundCount() {
        try {
            return inputRoundCount();
        } catch (Exception e) {
            OutputView.printException(e);
            return requestRoundCount();
        }
    }

    private int inputRoundCount() {
        OutputView.printRoundCountRequestMsg();
        return InputView.readRoundCount();
    }
}
