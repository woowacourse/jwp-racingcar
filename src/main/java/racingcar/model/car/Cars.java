package racingcar.model.car;

import racingcar.dto.CarDto;
import racingcar.model.MoveCount;
import racingcar.model.manager.CarMoveManager;
import racingcar.model.manager.ThresholdCarMoveManager;
import racingcar.util.RandomNumberGenerator;
import racingcar.util.ValueEditor;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final int MIN_CAR_NUMBER = 2;
    private static final String INVALID_CAR_NUMBER = "2개 이상의 자동차를 입력해 주세요.";

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateCarNumber(cars);
        this.cars = cars;
    }

    public static Cars from(List<String> carNames){
        return new Cars(carNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    private void validateCarNumber(List<?> names) {
        if (names.size() < MIN_CAR_NUMBER) {
            throw new IllegalArgumentException(INVALID_CAR_NUMBER);
        }
    }

    public void moveCars(CarMoveManager carMoveManager) {
        cars.forEach(car -> car.move(carMoveManager.isMove(RandomNumberGenerator.getRandomNumber())));
    }

    public int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public List<String> getWinners() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.isSame(maxPosition))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

}
