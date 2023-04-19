package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlayRequestDto {
    @JsonProperty("names")
    private String names;
    @JsonProperty("count")
    private int count;

    @JsonCreator
    public PlayRequestDto(@JsonProperty("names") final String names, @JsonProperty("count") final int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
