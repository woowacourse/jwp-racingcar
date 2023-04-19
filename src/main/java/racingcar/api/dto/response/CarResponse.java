package racingcar.api.dto.response;

import racingcar.domain.Car;
import racingcar.entity.CarEntity;

public class CarResponse {
    private final String name;
    private final Integer position;

    private CarResponse(final String name, final Integer position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse from(final CarEntity car) {
        return new CarResponse(car.getName(), car.getPosition());
    }

    public static CarResponse from(final Car car) {
        return new CarResponse(car.getName(), car.getPosition());
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
