package racingcar.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.RacingCars;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController() {
        this.racingCarService = new RacingCarService(
                new RacingCarRepository(new JdbcTemplate()),
                new RandomNumberGenerator()
        );
    }

    public void run() {
        List<String> carNames = inputCarNames();
        int roundCount = inputRoundCount();
        startRound(carNames, roundCount);
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

    private int inputRoundCount() {
        OutputView.printRoundCountRequestMsg();
        try {
            return InputView.readRoundCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputRoundCount();
        }
    }

    private void startRound(List<String> carNames, int roundCount) {
        OutputView.printRoundResultMsg();
        RacingCars racingCars = racingCarService.createRacingCars(carNames);
        racingCarService.moveCars(racingCars, roundCount);
        OutputView.printRacingResult(racingCars.getCars(), racingCars.getWinners());
    }
}
