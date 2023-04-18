package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestDTO {

    @NotBlank(message = "이름을 입력해야 합니다.")
    private String names;

    @NotNull(message = "횟수를 입력해야 합니다.")
    private Integer count;

    public RequestDTO() {
    }

    public RequestDTO(final String names, final Integer count) {
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
