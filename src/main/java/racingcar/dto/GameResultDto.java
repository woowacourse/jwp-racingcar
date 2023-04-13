package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {
    private final String winners;
    private final int trialCount;
    private final List<Car> cars;

    private GameResultDto(final String winners, final int trialCount, final List<Car> cars) {
        this.winners = winners;
        this.trialCount = trialCount;
        this.cars = cars;
    }

    public static GameResultDto of(final List<String> winners, final int trialCount, final List<Car> cars) {
        return new GameResultDto(String.join(",",winners), trialCount, cars);
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public List<Car> getCars() {
        return cars;
    }
}

