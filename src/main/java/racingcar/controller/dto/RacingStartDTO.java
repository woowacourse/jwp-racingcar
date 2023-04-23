package racingcar.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RacingStartDTO {

    @NotBlank(message = "이름을 입력해야 합니다.")
    private String names;

    @NotNull(message = "횟수를 입력해야 합니다.")
    private Integer count;

    public RacingStartDTO() {
    }

    public RacingStartDTO(final String names, final Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
