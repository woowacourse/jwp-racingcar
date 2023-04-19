package racingcar.dto;

import java.beans.ConstructorProperties;

public class RacingGameRequest {

    private final String names;
    private final String count;

    @ConstructorProperties({"names", "count"})
    public RacingGameRequest(final String names, final String count) {
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
