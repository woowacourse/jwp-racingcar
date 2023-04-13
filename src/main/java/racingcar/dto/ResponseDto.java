package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class ResponseDto {

    private final String winners;
    private final List<Car> racingCars;

    public ResponseDto(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public ResponseDto(final List<String> winners, final List<Car> racingCars) {
        this(String.join(",", winners), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
