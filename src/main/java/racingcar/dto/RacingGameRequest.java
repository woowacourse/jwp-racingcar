package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGameRequest {
    private final String names;
    private final int count;

    public RacingGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return Arrays.stream(names.split(","))
                .collect(Collectors.toList());
    }

    public int getCount() {
        return count;
    }
}
