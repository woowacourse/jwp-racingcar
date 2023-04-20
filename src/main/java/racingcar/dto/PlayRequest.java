package racingcar.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class PlayRequest {

    @NotEmpty(message = "[ERROR] 입력값이 없습니다.")
    private final String names;

    @Positive(message = "[ERROR] 올바르지 않은 시도횟수입니다.")
    private final int count;

    public PlayRequest(String names, int count) {
        validateNames(names);
        this.names = names;
        this.count = count;
    }

    private void validateNames(String names) {
        if (names == null || names.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
