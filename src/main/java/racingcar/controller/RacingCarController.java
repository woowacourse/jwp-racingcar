package racingcar.controller;

import java.util.List;
import racingcar.domain.AttemptNumber;
import racingcar.domain.Cars;
import racingcar.domain.RacingCarGame;
import racingcar.dto.CarDto;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final NumberGenerator numberGenerator;

    public RacingCarController() {
        this.numberGenerator = new RandomNumberGenerator();
    }

    public void run() {
        final RacingCarGame racingCarGame = new RacingCarGame(getCars(), getAttemptNumber(), numberGenerator);
        racingCarGame.play();
        printResult(racingCarGame);
    }

    private Cars getCars() {
        List<String> carNames = InputView.readCarNames();
        try {
            return Cars.from(carNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCars();
        }
    }

    private AttemptNumber getAttemptNumber() {
        try {
            int number = InputView.readAttemptNumber();
            return new AttemptNumber(number);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getAttemptNumber();
        }
    }

    private void printResult(final RacingCarGame racingCarGame) {
        final List<CarDto> cars = CarDto.getInstances(new Cars(racingCarGame.getCars()));
        final List<String> winners = racingCarGame.findWinners();
        OutputView.printResult(cars, winners);
    }
}
