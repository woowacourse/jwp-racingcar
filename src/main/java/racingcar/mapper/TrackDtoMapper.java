package racingcar.mapper;

import racingcar.dao.dto.TrackDto;
import racingcar.model.TrialTimes;

public class TrackDtoMapper {

    private TrackDtoMapper() {
    }

    public static TrackDto from(final TrialTimes trialTimes) {
        return new TrackDto(trialTimes.getValue());
    }
}
