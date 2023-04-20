package racingcar.model;

import racingcar.exception.BadRequestException;
import racingcar.strategy.RacingNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final Cars cars;
    private final RacingNumberGenerator racingNumberGenerator;
    private final Round round;

    public RacingGame(final String names, final String count, final RacingNumberGenerator racingNumberGenerator) {
        cars = generateCars(names);
        round = new Round(count);
        this.racingNumberGenerator = racingNumberGenerator;
    }

    public Cars generateCars(String inputCarsName) {
        String[] carsName = inputCarsName.split(",");
        checkDuplication(carsName);
        return new Cars(mapToCars(carsName));
    }

    private void checkDuplication(String[] carsName) {
        if (getDistinctCarsCount(carsName) != carsName.length) {
            throw new BadRequestException("이름은 중복될 수 없습니다.");
        }
    }

    private long getDistinctCarsCount(String[] carsName) {
        return Arrays.stream(carsName)
                .distinct()
                .count();
    }

    public void play() {
        int count = round.getRound();
        while (count-- > 0) {
            cars.race(racingNumberGenerator);
        }
    }

    private List<Car> mapToCars(String[] carsName) {
        return Arrays.stream(carsName)
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getWinnerCars() {
        return cars.findWinnerCars(cars.getWinner());
    }

    public List<Car> getCars() {
        return cars.findCars();
    }
}
