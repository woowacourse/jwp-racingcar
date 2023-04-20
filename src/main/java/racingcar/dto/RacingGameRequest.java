package racingcar.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RacingGameRequest {

    private static final String NAME_DELIMITER = ",";
    private static final String BLANK_NOT_ALLOWED_ERROR_MESSAGE = "빈 값은 입력할 수 없습니다.";

    @NotBlank(message = BLANK_NOT_ALLOWED_ERROR_MESSAGE)
    private String names;

    @NotNull(message = BLANK_NOT_ALLOWED_ERROR_MESSAGE)
    private int count;

    public RacingGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public static RacingGameRequest of(String names, int count) {
        validateEmptyValue(names);
        return new RacingGameRequest(names, count);
    }

    public RacingGameRequest() {
    }

    private static void validateEmptyValue(String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException(BLANK_NOT_ALLOWED_ERROR_MESSAGE);
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

}
