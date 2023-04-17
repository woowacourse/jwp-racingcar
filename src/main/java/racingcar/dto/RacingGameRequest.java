package racingcar.dto;

import java.util.List;

public class RacingGameRequest {

    private static final String NAME_DELIMITER = ",";

    private String names;
    private int count;

    public RacingGameRequest(String names, int count) {
        validateNotEmpty(names);
        validateCountPositive(count);
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

    private void validateCountPositive(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("횟수는 0보다 커야 합니다.");
        }
    }

    public List<String> getNames() {
        return List.of(names.split(NAME_DELIMITER));
    }

    public int getCount() {
        return count;
    }
}
