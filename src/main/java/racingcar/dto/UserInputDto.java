package racingcar.dto;

import java.util.Objects;

public class UserInputDto {

    private final String names;
    private final int count;

    public UserInputDto(String names, int count) {
        validateNullOrBlank(names);
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }

    private void validateNullOrBlank(String inputNames) {
        if (Objects.isNull(inputNames) || inputNames.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }
}

