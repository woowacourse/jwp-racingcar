package racingcar.controller;

import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarConsoleRepository;
import racingcar.service.RacingCarService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController() {
        this.racingCarService = new RacingCarService(
                new RacingCarConsoleRepository(),
                new RandomNumberGenerator()
        );
    }

    public void play() {
        OutputView.printRoundResultMsg();
        RacingResultResponse racingResultResponse =
                racingCarService.playRacingGame(
                        inputCarNames(),
                        inputTrialCount()
                );
        OutputView.printRacingResult(racingResultResponse);
    }

    private List<String> inputCarNames() {
        OutputView.printCarNameRequestMsg();
        try {
            return InputView.readCarNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCarNames();
        }
    }

    private int inputTrialCount() {
        OutputView.printRoundCountRequestMsg();
        try {
            return InputView.readTrialCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputTrialCount();
        }
    }
}
