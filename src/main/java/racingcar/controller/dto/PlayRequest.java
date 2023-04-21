package racingcar.controller.dto;

import java.beans.ConstructorProperties;

public class PlayRequest {

    private final String names;
    private final int count;

    @ConstructorProperties({"names", "count"})
    public PlayRequest(String names, int count) {
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
