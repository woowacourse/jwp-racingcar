package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.RacingCars;

public class RacingCarsDto {

    private final RacingCars racingCars;

    public RacingCarsDto(RacingCars racingCars) {
        this.racingCars = racingCars;
    }

    public List<String> getWinnerNames() {
        return racingCars.getWinnerNames();
    }

    public List<RacingCarDto> getRacingCarDtos() {
        return racingCars.getRacingCars()
            .stream()
            .map(RacingCarDto::new)
            .collect(Collectors.toList());
    }
}
