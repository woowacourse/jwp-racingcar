package racingcar.mapper;

import racingcar.controller.dto.TrackRequest;

public class TrackRequestMapper {

    private TrackRequestMapper() {
    }

    public static TrackRequest of(final String names, final String count) {
        return new TrackRequest(names, count);
    }
}
