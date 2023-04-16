package racingcar.dto.response;

import racingcar.domain.Car;
import racingcar.entity.CarResultEntity;

public class CarResponse {
    private final String name;
    private final int position;

    public CarResponse(final Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarResponse(final CarResultEntity car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
