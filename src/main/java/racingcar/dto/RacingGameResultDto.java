package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {

    private final int round;
    private final List<CarDto> carDtos;

    public RacingGameResultDto(int round, List<CarDto> carDtos) {
        this.round = round;
        this.carDtos = carDtos;
    }

    public int getRound() {
        return round;
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }
}
