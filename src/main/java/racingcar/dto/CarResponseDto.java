package racingcar.dto;

import lombok.Getter;
import racingcar.domain.car.Car;

@Getter
public class CarResponseDto {
    private final String name;
    private final int position;
    
    public CarResponseDto(final Car car) {
        this.name = car.getName().getName();
        this.position = car.getPosition().getPosition();
    }
}
