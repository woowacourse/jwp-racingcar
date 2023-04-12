package racingcar.model;

import java.util.List;

public class PlayRequest {
    private final String names;
    private final int count;

    public PlayRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
