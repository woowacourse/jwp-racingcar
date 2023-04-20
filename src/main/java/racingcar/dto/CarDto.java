package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import racingcar.domain.car.Car;

@Getter
@EqualsAndHashCode
public class CarDto {
    private final Long gameId;
    private final String name;
    private final int position;
    
    public CarDto(final Long gameId, final Car car) {
        this.gameId = gameId;
        this.name = car.getName().getName();
        this.position = car.getPosition().getPosition();
    }
}
