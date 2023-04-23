package racingcar.dto.response;

import racingcar.domain.Car;
import racingcar.domain.entity.CarEntity;

public class CarResponse {

    private final String name;
    private final int position;

    public CarResponse(Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarResponse(CarEntity carEntity) {
        this.name = carEntity.getName();
        this.position = carEntity.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
