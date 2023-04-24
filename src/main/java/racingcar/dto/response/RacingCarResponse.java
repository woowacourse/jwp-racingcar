package racingcar.dto.response;

import racingcar.domain.Car;

public class RacingCarResponse {

    private final String name;
    private final Integer position;

    private RacingCarResponse(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarResponse from(Car car) {
        return new RacingCarResponse(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }
}
