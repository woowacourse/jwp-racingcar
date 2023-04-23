package racingcar.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RacingGameRequest {

    @Size(min = 2, message = "참가자는 최소 2명이여야 합니다")
    private List<String> names;
    @Min(value = 1, message = "최소 1보다 커야됩니다")
    private int count;

    public RacingGameRequest(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {

    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
