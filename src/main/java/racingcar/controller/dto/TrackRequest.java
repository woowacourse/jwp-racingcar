package racingcar.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class TrackRequest {

    @NotBlank(message = "참여자 이름을 입력해야합니다.")
    private final String names;

    @NotBlank(message = "시도할 횟수를 입력해야합니다.")
    private final String count;

    @JsonCreator
    public TrackRequest(
            @JsonProperty(value = "names") final String names,
            @JsonProperty(value = "count") final String count) {
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
