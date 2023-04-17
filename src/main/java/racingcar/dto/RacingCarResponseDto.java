package racingcar.dto;

import java.util.Collections;
import java.util.List;

public final class RacingCarResponseDto {
    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public RacingCarResponseDto(final RacingCarsDto racingCarsDto) {
        this.winners = racingCarsDto.getWinnerNames();
        this.racingCars = racingCarsDto.getRacingCarDtos();
    }

    public List<String> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public List<RacingCarDto> getRacingCars() {
        return Collections.unmodifiableList(racingCars);
    }
}
