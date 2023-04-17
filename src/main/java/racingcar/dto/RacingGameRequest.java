package racingcar.dto;

import java.util.List;

public class RacingGameRequest {

    private static final String NAME_DELIMITER = ",";

    private String names;
    private int count;

    public RacingGameRequest(String names, int count) {
        validateNotEmpty(names);
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {
    }

    private void validateNotEmpty(String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException("빈값은 입력할 수 없습니다.");
        }
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

    public void setNames(String names) {
        validateNotEmpty(names);
        this.names = names;
    }
}
