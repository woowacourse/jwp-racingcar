package racingcar.dto;

import java.util.Arrays;
import java.util.List;

public class RaceRequest {

    private static final String SPLIT_DELIMITER = ",";

    private final String names;
    private final int count;

    public RaceRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> makeSplitNames() {
        return Arrays.asList(names.split(SPLIT_DELIMITER));
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
