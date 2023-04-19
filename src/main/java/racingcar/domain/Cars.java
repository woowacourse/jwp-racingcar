package racingcar.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import racingcar.dto.CarResponse;
import racingcar.utils.NumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.vo.CarName;

public class Cars {

    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    public Cars(List<Car> cars, NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
    }

    public Cars(String names, NumberGenerator numberGenerator) {
        List<CarName> carNames = getCarNames(names);
        validateDuplication(carNames);

        this.cars = carNames.stream().map(Car::of)
            .collect(Collectors.toList());
        this.numberGenerator = numberGenerator;
    }

    private List<CarName> getCarNames(String names) {
        List<CarName> carNames = CarName.of(
            Arrays.asList(names.split(","))
        );

        validateCarNames(carNames);
        return carNames;
    }

    private void validateCarNames(List<CarName> carNames) {
        validateNamesLength(carNames);
        carNames.forEach(this::validateNameBlank);
    }

    private void validateNamesLength(List<CarName> carNames) {
        if (carNames.size() == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private void validateNameBlank(CarName carName) {
        if (carName.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private void validateDuplication(List<CarName> names) {
        Set<CarName> namesWithoutDuplication = new HashSet<>(names);
        if (names.size() != namesWithoutDuplication.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public List<CarResponse> getCarDtos() {
        return cars.stream().map((car) -> new CarResponse(car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }

    public void move() {
        cars.forEach((car) -> car.move(numberGenerator.generateNumber()));
    }

    public List<String> getWinnerNames() {
        int highestPosition = calculateHighestPosition();
        return cars.stream()
            .filter(car -> hasHighestPosition(highestPosition, car))
            .map(Car::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    private boolean hasHighestPosition(int highestPosition, Car car) {
        return car.hasPosition(highestPosition);
    }

    private int calculateHighestPosition() {
        return Collections.max(cars.stream()
            .map((Car::getPosition))
            .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public String toString() {
        return "Cars{" +
            "cars=" + cars +
            ", randomNumberGenerator=" + numberGenerator +
            '}';
    }
}
