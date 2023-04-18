package racingcar.controller;

import javax.validation.constraints.NotBlank;

public class TrackRequest {

    @NotBlank(message = "참가자 이름 입력을 확인하세요.")
    private String names;
    @NotBlank(message = "시도 횟수 입력을 확인하세요.")
    private String count;

    protected TrackRequest() {
    }

    public TrackRequest(final String names, final String count) {
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
