package racingcar.controller;

import racingcar.dto.RacingGameResponseDto;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public void run() {
        Cars cars = setCars();
        RacingGame racingGame = new RacingGame(cars, setTryCount());
        racingGame.race(numberGenerator);
        outputView.printResult(new RacingGameResponseDto(cars.getWinners(),cars.getCars()));
    }

    private Cars setCars() {
        outputView.printRequestCarName();
        try {
            Cars cars = new Cars(inputView.inputCarName());
            return cars;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return setCars();
        }
    }

    private int setTryCount() {
        outputView.printRequestTryCount();
        try {
            return inputView.inputTryCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return setTryCount();
        }
    }
}
