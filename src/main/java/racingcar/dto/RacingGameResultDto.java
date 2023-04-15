package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {

    private final List<CarResultDto> carResultDtos;
    private final int round;

    public RacingGameResultDto(final List<CarResultDto> carResultDtos, final int round) {
        this.carResultDtos = carResultDtos;
        this.round = round;
    }

    public List<CarResultDto> getCarResultDtos() {
        return carResultDtos;
    }

    public int getRound() {
        return round;
    }
}
