package racingcar.domain;

import racingcar.domain.engine.Engine;
import racingcar.dto.CarDto;
import racingcar.exception.BusinessArgumentException;
import racingcar.exception.ErrorCode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars(List<Name> carNames, Engine engine) {
        cars = carNames.stream().map(name -> new Car(name, engine))
                .collect(Collectors.toList());
        validateNameDuplication(carNames);
    }

    public void moveCars() {
        for (Car car : cars) {
            car.tryMove();
        }
    }

    public String getWinners() {
        int maxPosition = getMaxPosition();
        List<String> winnerNames = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());

        return String.join(",", winnerNames);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(this.cars);
    }

    private int getMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElseThrow(() -> new BusinessArgumentException(ErrorCode.CAR_NOT_EXIST));
    }

    private void validateNameDuplication(List<Name> carNames) {
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_NAME_DUPLICATE.getMessage());
        }
    }
}
