package racingcar.dto;

import racingcar.domain.RacingCar;

public class RacingCarDto {
    private final String name;
    private final Integer position;

    private RacingCarDto(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarDto create(RacingCar racingCar) {
        return new RacingCarDto(racingCar.getName(), racingCar.getPosition());
    }

    public String getName() {
        return this.name;
    }

    public Integer getPosition() {
        return position;
    }
}
