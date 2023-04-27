package racingcar.dto.response;

import racingcar.domain.Car;
import racingcar.domain.entity.CarResultEntity;

public class CarResponse {

    private final String name;
    private final int position;

    public CarResponse(Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarResponse(CarResultEntity carResultEntity) {
        this.name = carResultEntity.getName();
        this.position = carResultEntity.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
