package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import racingcar.domain.car.Car;

@EqualsAndHashCode
@ToString
@Getter
public class CarDto {
    private final Long gameId;
    private final String name;
    private final int position;
    
    public CarDto(final Long gameId, final Car car) {
        this.gameId = gameId;
        this.name = car.getName().getName();
        this.position = car.getPosition().getPosition();
    }
    
    public CarDto(final Long gameId, final String name, final int position) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }
}

