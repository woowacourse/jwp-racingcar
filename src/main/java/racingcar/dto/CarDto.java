package racingcar.dto;

import racingcar.domain.Car;
import racingcar.infrastructure.persistence.entity.CarEntity;

public final class CarDto {

    private final String name;
    private final int position;

    private CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarDto from(final Car car) {
        return new CarDto(car.getCarName(), car.getPosition());
    }

    public static CarDto createByEntity(final CarEntity carEntity) {
        return new CarDto(carEntity.getName(), carEntity.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
