package racingcar.dto.response;

import racingcar.domain.Car;

public class RacingCarResponse {

    private String name;
    private Integer position;

    private RacingCarResponse(final String name, final Integer position) {
        this.name = name;
        this.position = position;
    }

    public static RacingCarResponse from(final Car car) {
        return new RacingCarResponse(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }
}
