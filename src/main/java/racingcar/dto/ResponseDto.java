package racingcar.dto;

import java.util.List;

public class ResponseDto {

    private final String winners;
    private final List<CarDto> racingCars;

    public ResponseDto(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public ResponseDto(final List<String> winners, final List<CarDto> racingCars) {
        this(String.join(",", winners), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
