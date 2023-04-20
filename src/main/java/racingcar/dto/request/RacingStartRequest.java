package racingcar.dto.request;

import java.util.Arrays;
import java.util.List;

public class RacingStartRequest {

    private List<String> names;
    private Integer count;

    public RacingStartRequest(String names, Integer count) {
        this.names = Arrays.asList(names.strip().split(","));
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
