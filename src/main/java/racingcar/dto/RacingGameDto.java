package racingcar.dto;

import java.util.List;

public final class RacingGameDto {

    private final List<CarDto> carDtos;
    private final int trialCount;

    public RacingGameDto(final List<CarDto> carDtos, final int trialCount) {
        this.carDtos = carDtos;
        this.trialCount = trialCount;
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
