package racingcar.service.dto;

import racingcar.dto.CarDto;

import java.util.List;
import java.util.stream.Collectors;

public class SingleGameResult {

    private final String winners;
    private final List<CarDto> racingCars;

    public SingleGameResult(List<String> winners, List<CarDto> racingCars) {
        this.winners = winners.stream().collect(Collectors.joining(","));
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
