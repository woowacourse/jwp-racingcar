package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarRequestDto {
    @NotBlank(message = "자동차 이름으로 빈 값은 입력할 수 없습니다.")
    private final String names;

    @Min(value = 1, message = "시도 횟수는 1이상이여야 합니다.")
    private final String count;

    public RacingCarRequestDto(final String names, final String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }

}
