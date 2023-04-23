package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GameRequest {

    private final String names;
    private final int count;

    @JsonCreator
    public GameRequest(final String names, final int count) {
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
