package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

public class TrackResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    private TrackResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static TrackResponse of(Cars cars) {
        final String winnerCarNames = makeWinnerCarNames(cars);
        final List<CarResponse> results = makeCarResponses(cars);
        return new TrackResponse(winnerCarNames, results);
    }

    private static String makeWinnerCarNames(final Cars finishedCars) {
        return finishedCars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));
    }

    private static List<CarResponse> makeCarResponses(final Cars finishedCars) {
        return finishedCars.getCarsCurrentInfo().stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
