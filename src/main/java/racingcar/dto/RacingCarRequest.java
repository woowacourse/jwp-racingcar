package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarRequest {
    @NotBlank(message = "자동차 이름은 공백을 입력할 수 없습니다.")
    private final String names;

    @Min(value = 1, message = "시도 횟수는 1 이상의 정수를 입력해야 합니다.")
    private final int count;

    public RacingCarRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
