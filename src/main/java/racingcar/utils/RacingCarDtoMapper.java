package racingcar.utils;

import racingcar.dto.RacingCarDto;

public class RacingCarDtoMapper {
    private static final String RACING_CAR_DTO_DELIMITER = ":";
    private static final int RACING_CAR_NAME_INDEX = 0;
    private static final int RACING_CAR_POSITION_INDEX = 1;

    private RacingCarDtoMapper() {
    }

    public static RacingCarDto fromString(String input) {
        String[] split = input.split(RACING_CAR_DTO_DELIMITER);
        return new RacingCarDto(split[RACING_CAR_NAME_INDEX], Integer.parseInt(split[RACING_CAR_POSITION_INDEX]));
    }
}
