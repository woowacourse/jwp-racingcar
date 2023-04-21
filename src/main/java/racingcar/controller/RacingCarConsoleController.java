package racingcar.controller;


import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;
import racingcar.service.GamePlay;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import static racingcar.view.OutputView.printResultNotice;
import static racingcar.view.OutputView.printWinner;

public class RacingCarConsoleController {

    private final NumberGenerator numberGenerator;

    public RacingCarConsoleController(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void startGame() {
        OutputView.printInputCarNamesNotice();
        Cars cars = new Cars(CarFactory.buildCars(InputView.inputCarNames()));
        OutputView.printInputTryTimesNotice();
        int tryTimes = InputView.inputTryTimes();
        printResultNotice();
        GamePlay.play(cars, tryTimes, numberGenerator);
        OutputView.printCarNameAndPosition(cars);
        printWinner(cars.findWinners());
    }

}
