package racingcar.dto;

import java.util.List;

public class RacingGameRequest {
    private List<String> names;
    private int count;

    public RacingGameRequest(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {

    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
