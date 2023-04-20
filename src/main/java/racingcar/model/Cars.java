package racingcar.model;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.Strategy.NumberGenerator;

public class Cars {
    private static final int MINIMUM_PARTICIPANT = 2;

    private final List<Car> cars;

    public static Cars from(List<String> carNames) {
        List<Car> cars = carNames.stream()
            .map(String::trim)
            .map(Car::new)
            .collect(Collectors.toList());
        return new Cars(cars);
    }


    private Cars(List<Car> cars) {
        validateOverMinimumParticipant(cars);
        this.cars = cars;
    }

    private void validateOverMinimumParticipant(List<Car> cars) {
        if (cars.size() < MINIMUM_PARTICIPANT) {
            throw new IllegalArgumentException("경주는 최소 " + MINIMUM_PARTICIPANT + "명이 필요해요.");
        }
    }

    public void move(int trial, NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(trial, numberGenerator);
        }
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return findMaxPositionCars(maxPosition);
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.findHigherPosition(maxPosition);
        }
        return maxPosition;
    }

    private List<Car> findMaxPositionCars(int winnerPosition) {
        return cars.stream()
            .filter(car -> car.isMaxPosition(winnerPosition))
            .collect(Collectors.toUnmodifiableList());
    }
}
