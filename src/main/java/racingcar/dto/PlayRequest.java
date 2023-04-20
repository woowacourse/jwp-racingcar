package racingcar.dto;

import java.util.List;
import javax.validation.constraints.Positive;

public class PlayRequest {

    private final List<String> names;

    @Positive(message = "[ERROR] 올바르지 않은 시도횟수입니다.")
    private final int count;

    public PlayRequest(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    private void validateNames(String names) {
        if (names == null || names.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
