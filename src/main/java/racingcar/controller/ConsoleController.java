package racingcar.controller;

import java.util.List;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.ui.View;

public class ConsoleController {
    private final View view;

    public ConsoleController(View view) {
        this.view = view;
    }

    public void run() {
        int Trial = view.tryCount();
        Trial trial = new Trial(Trial);

        List<String> carNames = view.carNames();
        Cars cars = Cars.from(carNames);

        playGame(trial, cars);
        findWinners(cars);
    }

    private void playGame(Trial trial, Cars cars) {

        cars.move(trial.getTrial(), new RandomNumberGenerator());
        /*for (int i = 0; i < trial; i++) {
            List<Car> movedCars = racingcarService.move();
            view.result(movedCars);
        }*/
    }

    private void findWinners(Cars cars) {
        List<Car> winners = cars.findWinners();
        view.winner(winners);
    }
}
