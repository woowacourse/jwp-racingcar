package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostGameRequest {

    @JsonProperty("names")
    private final String names;
    @JsonProperty("count")
    private final int count;

    @JsonCreator
    public PostGameRequest(final @JsonProperty("names") String names, final @JsonProperty("count") int count) {
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
