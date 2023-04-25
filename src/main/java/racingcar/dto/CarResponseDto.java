package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.car.Car;

@EqualsAndHashCode
@ToString
@Getter
public class CarResponseDto {
    private final String name;
    private final int position;
    
    public CarResponseDto(final Car car) {
        this.name = car.getName().getName();
        this.position = car.getPosition().getPosition();
    }
}
