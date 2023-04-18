package racing.controller;

import racing.domain.Cars;
import racing.ui.output.OutputView;

import java.util.Random;

/**
 * @author 베베
 * @version 1.0.0
 * @since by 베베 on 2023. 04. 14.
 */
public class RacingController {

    private final Cars cars;

    public RacingController(Cars cars) {
        this.cars = cars;
    }

    public void start(int count) {
        OutputView.printPhrase();
        move(count);
        OutputView.printResult(cars);
    }

    private void move(int count) {
        while(count-- > 0) {
            cars.calculator(new Random().nextInt());
            OutputView.printProcessing(cars);
        }
    }

}
