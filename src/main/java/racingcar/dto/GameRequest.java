package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GameRequest {
    
    @NotBlank(message = "하나 이상의 이름을 입력해야 합니다.")
    private String names;

    @Min(value = 1, message = "시도 횟수는 1이상으로 입력해야 합니다.")
    private Integer count;

    private GameRequest() {
    }

    public GameRequest(final String names, final Integer count) {
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
