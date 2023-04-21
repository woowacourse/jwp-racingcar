package racingcar.mapper;

import racingcar.controller.dto.CarResponse;
import racingcar.controller.dto.TrackResponse;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class TrackResponseMapper {

    private TrackResponseMapper() {}

    public static TrackResponse from(final Cars cars) {
        final String winnerCarNames = makeWinnerCarNames(cars);
        final List<CarResponse> results = makeCarResponses(cars);
        return new TrackResponse(winnerCarNames, results);
    }

    private static String makeWinnerCarNames(final Cars finishedCars) {
        final String winnerCarNames = finishedCars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));
        return winnerCarNames;
    }

    private static List<CarResponse> makeCarResponses(final Cars finishedCars) {
        final List<CarResponse> results = finishedCars.getCarsCurrentInfo().stream()
                .map(car ->  CarResponseMapper.of(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
        return results;
    }
}
