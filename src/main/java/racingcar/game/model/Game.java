package racingcar.game.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.NumberGenerator;
import racingcar.car.model.RacingCar;

public interface Game {
    
    String DELITMITER = ",";
    int START_POSITION = 0;
    String DUPLICATED_CAR_NAME_ERROR = "중복된 자동차 이름이 있습니다.";
    int MIN_CAR_NUMBER = 2;
    String CAR_SIZE_UNDER_MIN_NUMBER_ERROR = "자동차는 " + MIN_CAR_NUMBER + "대 이상이어야 합니다.";
    String COUNT_IS_LESS_THAN_ONE_ERROR = "시도 횟수는 1 이상이어야 합니다.";
    
    static GameResult run(final NumberGenerator numberGenerator, final int count, final String namesLiteral) {
        final List<Car> cars = generateCars(namesLiteral);
        validateCount(count);
        final Game racingCarGame = RacingCarGame.create(numberGenerator, cars);
        final Game updatedGame = racingCarGame.race(count);
        return updatedGame.calculateResult();
    }
    
    private static void validateCount(final int count) {
        if (count < 1) {
            throw new IllegalArgumentException(COUNT_IS_LESS_THAN_ONE_ERROR);
        }
    }
    
    private static List<Car> generateCars(final String namesLiteral) {
        final List<Car> cars = Arrays.stream(namesLiteral.split(DELITMITER))
                .map(String::trim)
                .map(name -> RacingCar.create(name, START_POSITION))
                .collect(Collectors.toList());
        validateMinimumSize(cars);
        validateUniqueness(cars);
        return cars;
    }
    
    private static void validateUniqueness(final List<Car> cars) {
        if (cars.size() != cars.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATED_CAR_NAME_ERROR);
        }
    }
    
    private static void validateMinimumSize(final List<Car> cars) {
        if (cars.size() < MIN_CAR_NUMBER) {
            throw new IllegalArgumentException(CAR_SIZE_UNDER_MIN_NUMBER_ERROR);
        }
    }
    
    Game moveCars();
    
    Game race(int count);
    
    GameResult calculateResult();
    
    List<Car> getCars();
}
