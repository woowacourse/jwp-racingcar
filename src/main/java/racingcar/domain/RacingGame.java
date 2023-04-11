package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.NumberGenerator;
import racingcar.RandomNumberGenerator;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponse;

public class RacingGame {
    public static final int MAX_TRY_COUNT_BOUND = 100;

    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private int tryCount;

    public RacingGame(NumberGenerator numberGenerator, int tryCount, Cars cars) {
        validateTryCount(tryCount);
        this.numberGenerator = numberGenerator;
        this.cars = cars;
        this.tryCount = tryCount;
    }

    public RacingGame(int tryCount, Cars cars) {
        this(new RandomNumberGenerator(), tryCount, cars);
    }

    private void validateTryCount(int tryCount) {
        if (tryCount > MAX_TRY_COUNT_BOUND) {
            throw new IllegalArgumentException("시도 횟수는 100회 이하만 가능합니다 현재 : " + tryCount + "회");
        }
    }

    public void playOneRound() {
        cars.moveAll(numberGenerator);
        tryCount--;
    }

    public boolean isEnd() {
        return tryCount == 0;
    }

    public List<String> getWinnerNames() {
        return cars.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public RacingGameResponse getResult() {
        List<CarDto> racingCars = cars.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        return new RacingGameResponse(getWinnerNames(), racingCars);
    }
}
