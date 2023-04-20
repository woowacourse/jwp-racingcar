package racingcar.dto.car;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StartGameRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String names;

    @NotNull(message = "시도 회수를 입력해주세요.")
    @Min(value = 2, message = "최소 횟수는 2입니다.")
    @Max(value = 10, message = "최대 횟수는 10입니다.")
    private Integer count;

    public StartGameRequestDto() {
    }

    public StartGameRequestDto(final String names, final int count) {
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
