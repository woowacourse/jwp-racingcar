package racingcar.infrastructure.persistence.entity;

import racingcar.domain.Car;
import racingcar.dto.CarDto;

public class CarEntity {

    private final String name;
    private final int position;
    private final Long gameId;

    public CarEntity(final Car car, final Long gameId) {
        this.name = car.getCarName();
        this.position = car.getPosition();
        this.gameId = gameId;
    }

    public CarEntity(final String name, final int position, final Long gameId) {
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public CarEntity(final CarDto carDto, final Long gameId) {
        this.name = carDto.getName();
        this.position = carDto.getPosition();
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }
}
