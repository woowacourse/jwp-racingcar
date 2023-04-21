package racingcar.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TrackRequest {

    @NotBlank(message = "참가자 이름 입력을 확인하세요.")
    private String names;

    @NotNull(message = "빈 값을 입력할 수 없습니다.")
    @Positive(message = "시도 횟수는 0보다 큰 수를 입력해야 합니다.")
    private Integer count;

    protected TrackRequest() {
    }

    public TrackRequest(final String names, final Integer count) {
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
