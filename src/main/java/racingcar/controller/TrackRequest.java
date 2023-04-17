package racingcar.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackRequest {

    private String names;
    private String count;

    @JsonCreator
    public TrackRequest(
            @JsonProperty(value = "names") final String names,
            @JsonProperty(value = "count") final String count) {
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
