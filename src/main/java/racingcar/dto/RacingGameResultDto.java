package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {

    private final int round;
    private final List<CarResultDto> carResultDtos;

    public RacingGameResultDto(int round, List<CarResultDto> carResultDtos) {
        this.round = round;
        this.carResultDtos = carResultDtos;
    }

    public int getRound() {
        return round;
    }

    public List<CarResultDto> getCarDtos() {
        return carResultDtos;
    }
}
