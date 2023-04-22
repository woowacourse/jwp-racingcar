package racingcar.mapper;

import racingcar.controller.dto.CarResponse;

public class CarResponseMapper {

    private CarResponseMapper() {
    }

    public static CarResponse of(final String name, final int position) {
        return new CarResponse(name, position);
    }
}
