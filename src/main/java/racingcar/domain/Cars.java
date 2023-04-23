package racingcar.domain;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;

public class Cars {

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        return new Cars(carNames.stream()
                .map(Car::new)
                .collect(toUnmodifiableList()));
    }

    public void runRound(final NumberPicker numberPicker) {
        for (Car car : cars) {
            car.runForward(numberPicker.pickNumber());
        }
    }

    public List<String> getWinners() {
        int maxDistance = findMaxDistance();
        return cars.stream().filter(car -> car.isSameDistance(maxDistance))
                .map(Car::getName)
                .map(Name::getValue)
                .collect(toUnmodifiableList());
    }

    private int findMaxDistance() {
        return cars.stream()
                .mapToInt(car -> car.getDistance().getValue())
                .max()
                .orElse(-1);
    }

    public List<Car> getCars() {
        return cars;
    }
}
