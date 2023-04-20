package racingcar.controller;

import java.util.List;
import java.util.function.Supplier;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.model.Winners;
import racingcar.ui.View;

public class ConsoleController {
    private final View view;

    public ConsoleController(View view) {
        this.view = view;
    }

    public void run() {
        Cars cars = repeatIfNull(this::getCars);
        Trial trial = repeatIfNull(this::getTrial);
        playGame(trial, cars);
        findWinners(cars);
    }

    private <T> T repeatIfNull(Supplier<T> supplier) {
        T t;
        do {
            t = supplier.get();
        } while (t == null);
        return t;
    }

    private Cars getCars() {
        List<String> carNames = view.carNames();
        try{
            return Cars.from(carNames);
        } catch (IllegalArgumentException e){
            view.error(e.getMessage());
            return null;
        }
    }

    private Trial getTrial() {
        int count = view.tryCount();
        try {
            return new Trial(count);
        } catch (IllegalArgumentException e) {
            view.error(e.getMessage());
            return null;
        }
    }

    private void playGame(Trial trial, Cars cars) {
        cars.move(trial.getTrial(), new RandomNumberGenerator());
    }

    private void findWinners(Cars cars) {
        Winners winners = Winners.from(cars);
        view.printWinner(winners.getWinner());
        view.printAllCars(cars);
    }
}
