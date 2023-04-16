package racingcar.dto;

import java.util.List;
import javax.validation.constraints.PositiveOrZero;

public class RacingGameRequest {
    private String names;

    @PositiveOrZero(message = "시도 횟수는 음수일 수 없습니다.")
    private int count;

    public RacingGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {
    }

    public List<String> readSplitNames() {
        String regex = ",";
        return List.of(names.split(regex));
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
