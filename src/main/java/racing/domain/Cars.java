package racing.domain;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import racing.domain.utils.RacingCarNumberGenerator;

public class Cars {

    private static final String CAR_NAME_DUPLICATED = "중복된 자동차 이름이 존재합니다.";

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateDuplication(cars);
        this.cars = cars;
    }

    private void validateDuplication(List<Car> cars) {
        long distinctCount = (int) cars.stream()
                .map(Car::getName)
                .map(String::trim)
                .distinct()
                .count();

        if (distinctCount != cars.size()) {
            throw new IllegalArgumentException(CAR_NAME_DUPLICATED);
        }
    }

    public void moveCarsWith(RacingCarNumberGenerator numberGenerator) {
        for (Car car : cars) {
            int generatedNumber = numberGenerator.peekNumber();
            car.moveForwardByNumber(generatedNumber);
        }
    }

    public Cars filterMaxStepCars() {
        int maxStep = getMaxStep();

        return new Cars(cars.stream()
                .filter(car -> car.getStep() == maxStep)
                .collect(Collectors.toList()));
    }

    private int getMaxStep() {
        return cars.stream()
                .mapToInt(Car::getStep)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Car> getCars() {
        return cars;
    }
}
