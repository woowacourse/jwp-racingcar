package racingcar.model;

import java.util.List;

public class Winners {
    private final List<Car> winners;

    public static Winners from(Cars cars) {
        return new Winners(cars.findWinners());
    }

    private Winners(List<Car> winners) {
        this.winners = winners;
    }

    public List<Car> getWinner() {
        return List.copyOf(winners);
    }
}
