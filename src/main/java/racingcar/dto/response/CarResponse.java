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

    public CarResponse(final Car car) {
        this(car.getName(), car.getPosition());
    }

    // TODO 생성 방식 변경 필요, car로만 생성 or carResult로만 생성?
    public CarResponse(final CarResult carResult) {
        this(carResult.getName(), carResult.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
