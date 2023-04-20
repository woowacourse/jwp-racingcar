package racingcar.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class PlayRequest {

    @Size(min = 1, max = 5, message = "[ERROR] 올바르지 않은 참여자수입니다.(1~5)")
    private final List<@Size(min = 1, max = 5, message = "[ERROR] 자동차 이름 길이는 1자 이상, 5자 이하여야합니다.") String> names;

    @Min(value = 1, message = "[ERROR] 올바르지 않은 시도횟수입니다.(1 ~ 10)")
    @Max(value = 10, message = "[ERROR] 올바르지 않은 시도횟수입니다.(1 ~ 10)")
    private final int count;

    public PlayRequest(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
