package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CarGroup {

    private static final String DUPLICATED_CAR_NAME_ERROR = "[ERROR] 자동차 이름에는 중복이 허용되지 않습니다.";
    private static final String RANGE_OF_CAR_GROUP_ERROR = "[ERROR] 자동차 대수는 2-50대 사이입니다.";
    private static final int MINIMUM_NUMBER_OF_CARS = 2;
    private static final int MAXIMUM_NUMBER_OF_CARS = 50;

    private final List<Car> cars;

    public CarGroup(String inputName) {
        List<String> names = List.of(inputName.split(","));
        validateDuplicatedName(names);
        validateNumberOfCars(names);
        this.cars = setUp(names);
    }

    public void race(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(numberGenerator);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    private List<Car> setUp(List<String> names) {
        return names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicatedName(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATED_CAR_NAME_ERROR);
        }
    }

    private void validateNumberOfCars(List<String> names) {
        if (names.size() < MINIMUM_NUMBER_OF_CARS || names.size() > MAXIMUM_NUMBER_OF_CARS) {
            throw new IllegalArgumentException(RANGE_OF_CAR_GROUP_ERROR);
        }
    }
}
