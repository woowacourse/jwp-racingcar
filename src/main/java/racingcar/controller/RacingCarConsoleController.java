package racingcar.controller;


import racingcar.domain.CarsFactory;
import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;
import racingcar.service.RacingCarPlayRule;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import static racingcar.view.OutputView.printResultNotice;
import static racingcar.view.OutputView.printWinner;

public class RacingCarConsoleController {
    private final NumberGenerator numberGenerator;
    private final RacingCarPlayRule racingCarPlayRule;

    public RacingCarConsoleController(NumberGenerator numberGenerator, RacingCarPlayRule racingCarPlayRule) {
        this.numberGenerator = numberGenerator;
        this.racingCarPlayRule = racingCarPlayRule;
    }

    public void startGame() {
        try {
            OutputView.printInputCarNamesNotice();
            Cars cars = CarsFactory.buildCarsFromFactory(InputView.inputCarNames());
            OutputView.printInputTryTimesNotice();
            int tryTimes = InputView.inputTryTimes();
            racingCarPlayRule.moverCarsUntilCountIsOver(cars, tryTimes, numberGenerator);
            printWinner(cars.findWinners());
            printResultNotice();
            OutputView.printCarNameAndPosition(cars);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
