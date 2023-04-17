package racingcar.dto;

import racingcar.domain.Car;

public class PlayerResultDto {
    private final String name;
    private final int position;

    private PlayerResultDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static PlayerResultDto from(Car car) {
        return new PlayerResultDto(car.getName(), car.getPosition());
    }

    public static PlayerResultDto of(String name, int position) {
        return new PlayerResultDto(name, position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
