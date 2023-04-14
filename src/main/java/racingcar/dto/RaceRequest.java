package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RaceRequest {

    private static final String SPLIT_DELIMITER = ",";

    private String names;
    private int count;

    public RaceRequest() {
    }

    public RaceRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getCarNames() {
        final String[] splitNames = names.split(SPLIT_DELIMITER);
        return Arrays.stream(splitNames)
            .collect(Collectors.toList());
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
