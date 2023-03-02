package racingcar.controller;


import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {
    private static final int START_INDEX = 0;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Cars cars = new Cars();
    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public void play() {
        inputCars();
        moveByTryCount(inputTryCount());
        winners();
    }

    public void inputCars() {
        String[] carNames = inputCarNames();
        for (String carName : carNames) {
            cars.addCar(new Car(carName));
        }
    }

    private void moveByTryCount(int tryCount) {
        outputView.newLine();
        outputView.resultMessage();
        for (int index = START_INDEX; index < tryCount; index++) {
            move();
            printStatus();
        }
    }

    private void move() {
        cars.moveAll(numberGenerator);
    }

    private void winners() {
        outputView.printWinners(cars.pickWinners());
    }

    private void printStatus() {
        outputView.printStatus(cars);
        outputView.newLine();
    }

    private String[] inputCarNames() {
        try {
            outputView.printStartMessage();
            return inputView.inputCarName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCarNames();
        }
    }

    private int inputTryCount() {
        outputView.printCountMessage();
        return inputView.inputCount();
    }

}
