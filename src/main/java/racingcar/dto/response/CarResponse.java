package racingcar.dto.response;

import racingcar.domain.Car;
import racingcar.domain.CarResult;

public class CarResponse {
    private final String name;
    private final int position;

    public CarResponse(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static CarResponse fromCar(final Car car) {
        return new CarResponse(car.getName(), car.getPosition());
    }

    public static CarResponse fromCarResult(final CarResult carResult) {
        return new CarResponse(carResult.getName(), carResult.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
