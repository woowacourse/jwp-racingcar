package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {

    @JsonProperty("names")
    private final String names;
    @JsonProperty("count")
    private final int count;

    @JsonCreator
    public GameRequest(@JsonProperty("names") String names,@JsonProperty("count") int count) {
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
