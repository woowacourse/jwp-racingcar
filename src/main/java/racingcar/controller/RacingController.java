package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.TrialCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import java.util.List;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = createCars();
        int inputValue = inputView.getTryCount();
        TrialCount trialCount = new TrialCount(inputValue);

        race(cars, trialCount);

        outputView.printResult(cars.getCars(), cars.getWinner());
    }

    private Cars createCars() {
        List<String> carNames = inputView.getCarNames();
        return new Cars(carNames);
    }

    private void race(final Cars cars, final TrialCount trialCount) {
        int value = trialCount.getValue();

        for (int count = 0; count < value; count++) {
            cars.moveAll();
        }
    }
}
