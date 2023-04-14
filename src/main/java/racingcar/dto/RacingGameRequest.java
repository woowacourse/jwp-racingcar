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

    public String getNames() {
        return names;
    }

    public List<String> getNamesList() {
        return Arrays.stream(names.split(","))
                .collect(Collectors.toList());
    }

    public int getCount() {
        return count;
    }
}
