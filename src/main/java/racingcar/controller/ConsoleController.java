package racingcar.controller;

import racingcar.model.Cars;
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
        repeatMove(cars, setTryCount());
        outputView.printWinner(cars.getWinners());
        outputView.printResult(cars);
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

    private void repeatMove(Cars cars, int tryCount) {
        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
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
