package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGameRequest {
    private String names;
    private int count;

    public RacingGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {

    }

    public List<String> toNameList() {
        return Arrays.stream(names.split(","))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
