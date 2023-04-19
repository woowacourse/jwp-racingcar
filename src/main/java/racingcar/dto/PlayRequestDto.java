package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class PlayRequestDto {

    @NotBlank(message = "플레이 요청 오류: 자동차 이름 목록은 빈 문자열일 수 없습니다.")
    private String names;
    @Positive(message = "플레이 요청 오류: 플레이 횟수는 0 이하일 수 없습니다.")
    private int count;

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
