package racingcar.dto.request;

import java.util.Arrays;
import java.util.List;

public class RacingGameRequest {

    private final List<String> names;
    private final int count;

    public RacingGameRequest(String names, int count) {
        this.names = Arrays.asList(names.trim().split(","));
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
