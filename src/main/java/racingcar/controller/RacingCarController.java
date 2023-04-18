package racingcar.controller;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingCars;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController() {
        this.racingCarService = new RacingCarService(
                new RacingCarRepository(new JdbcTemplate()),
                new RandomNumberGenerator()
        );
    }

    public void run() {
        List<String> carNames = createRacingCars();
        int roundCount = createRoundCount();
        startRound(carNames, roundCount);
    }

    private List<String> createRacingCars() {
        try {
            return inputCarNames();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createRacingCars();
        }
    }

    private List<String> inputCarNames() {
        OutputView.printCarNameRequestMsg();
        return InputView.readCarNames();
    }

    private int createRoundCount() {
        try {
            return inputRoundCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createRoundCount();
        }
    }

    private int inputRoundCount() {
        OutputView.printRoundCountRequestMsg();
        return InputView.readRoundCount();
    }

    private void startRound(List<String> carNames, int roundCount) {
        OutputView.printRoundResultMsg();
        RacingCars racingCars = racingCarService.createRacingCars(carNames);
        racingCarService.moveCars(racingCars, roundCount);
        OutputView.printRoundState(racingCars.getCars());
        OutputView.printRacingResult(racingCars.getWinners());
    }
}
