package racingcar.domain;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cars {

    private static final String DELIMITER = ",";

    private final List<Car> cars;

    public Cars(String carNames) {
        this(Stream.of(carNames.split(DELIMITER))
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void runRound(final NumberPicker numberPicker) {
        for (Car car : cars) {
            car.runForward(numberPicker.pickNumber());
        }
    }

    public List<String> getWinner() {
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
