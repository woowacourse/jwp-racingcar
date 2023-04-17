package racingcar.dto;

import racingcar.domain.RacingCar;

public final class RacingCarDto {
    private final String name;
    private final int position;

    public RacingCarDto(final RacingCar racingCar) {
        this.name = racingCar.getName();
        this.position = racingCar.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
