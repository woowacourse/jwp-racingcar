package racingcar.dto.response;

import racingcar.domain.Car;
import racingcar.dto.CarResultDto;

public class CarResponse {

    private final String name;
    private final int position;

    public CarResponse(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public CarResponse(Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
    }

    public CarResponse(CarResultDto carResultDto) {
        this.name = carResultDto.getName();
        this.position = carResultDto.getPosition();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
