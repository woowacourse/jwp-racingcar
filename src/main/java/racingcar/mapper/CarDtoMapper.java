package racingcar.mapper;

import racingcar.dao.dto.CarDto;

public class CarDtoMapper {

    private CarDtoMapper() {}

    public static CarDto of(final String name,
                            final int position,
                            final boolean isWinner,
                            final int trackId) {
        return new CarDto(name, position, isWinner, trackId);
    }
}
