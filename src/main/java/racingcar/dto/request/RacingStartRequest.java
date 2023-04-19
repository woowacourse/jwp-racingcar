package racingcar.dto.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RacingStartRequest {

    private final List<String> names;
    private final Integer count;

    public RacingStartRequest(String names, Integer count) {
        this.names = Arrays.asList(names.trim().split(","));
        this.count = count;
    }

    public RacingStartRequest(List<String> names, Integer count) {
        this.names = new ArrayList<>(names);
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
