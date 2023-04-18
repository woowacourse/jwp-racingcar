package racing.controller;

import racing.RandomNumberGenerator;
import racing.domain.Car;
import racing.domain.Cars;
import racing.service.RacingGameService;
import racing.ui.output.OutputView;

public class ConsoleRacingGameController {

    private final Cars cars;
    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(Cars cars) {
        this.cars = cars;
        this.racingGameService = new RacingGameService();
    }

    public void start(int count) {
        OutputView.printPhrase();
        move(count);
        OutputView.printResult(cars);
    }

    private void move(int count) {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        for (int i = 0; i < count; i++) {
            for (Car car : cars.getCars()) {
                racingGameService.move(generator.generate(), car);
            }
        }
    }

}
