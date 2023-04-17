package racingcar.controller;

import java.io.IOException;
import java.util.List;

import racingcar.domain.AttemptNumber;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingCarGame;
import racingcar.dto.CarDto;
import racingcar.dto.ResponseDto;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final NumberGenerator numberGenerator;

    public RacingCarConsoleController() {
        this.numberGenerator = new RandomNumberGenerator();
    }

    public void run() throws IOException {
        RacingCarGame racingCarGame = new RacingCarGame(getCars(), getAttemptNumber(), numberGenerator);
        List<String> winners = racingCarGame.findWinners();
        List<Car> cars = racingCarGame.getCars();
        List<CarDto> carDtos = CarDto.from(cars);

        OutputView.printResult(new ResponseDto(winners, carDtos));
    }

    private Cars getCars() throws IOException {
        List<String> carNames = InputView.readCarNames();
        try {
            return Cars.from(carNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCars();
        }
    }

    private AttemptNumber getAttemptNumber() throws IOException {
        try {
            int number = InputView.readAttemptNumber();
            return new AttemptNumber(number);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getAttemptNumber();
        }
    }
}
