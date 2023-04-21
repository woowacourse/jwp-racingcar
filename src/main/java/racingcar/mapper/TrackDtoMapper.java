package racingcar.mapper;

import racingcar.dao.dto.TrackDto;

public class TrackDtoMapper {

    private TrackDtoMapper() {
    }

    public static TrackDto from(final int trialTimes) {
        return new TrackDto(trialTimes);
    }
}
