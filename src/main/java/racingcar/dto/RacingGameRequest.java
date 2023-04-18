package racingcar.dto;

import java.util.List;

public class RacingGameRequest {

    private static final String NAME_DELIMITER = ",";

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

    public List<String> convertToSplitedNames() {
        return List.of(names.split(NAME_DELIMITER));
    }

    public int getCount() {
        return count;
    }

}
