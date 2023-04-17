package racingcar.controller;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TrackRequest {

    private String names;
    private String count;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TrackRequest(final String names, final String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
