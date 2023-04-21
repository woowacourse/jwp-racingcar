package racing.controller;

import racing.ui.input.InputView;
import racing.util.RandomNumberGenerator;
import racing.domain.Car;
import racing.domain.Cars;
import racing.service.RacingGameService;
import racing.ui.output.OutputView;

public class ConsoleRacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController() {
        this.racingGameService = new RacingGameService();
    }

    public void start() {
        OutputView.printPhrase();
        Cars cars = racingGameService.createCars(InputView.inputCarsName());
        int count = Integer.parseInt(InputView.inputCount());

        moveCar(cars, count);
        OutputView.printResult(cars);
    }

    private void moveCar(Cars cars, int count) {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        for (int i = 0; i < count; i++) {
            for (Car car : cars.getCars()) {
                racingGameService.move(generator.generate(), car);
            }
        }
    }

}
