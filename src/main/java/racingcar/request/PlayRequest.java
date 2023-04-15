package racingcar.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PlayRequest {

    @NotBlank(message = "자동차 이름을 입력해주세요.")
    private final String names;

    @Min(value = 1, message = "시도 횟수는 1 이상이어야 합니다")
    private final int count;

    public PlayRequest(final String names, final int count) {
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
