package racingcar.dto;

import java.util.List;

public class RacingGameRequestDto {

    private final List<String> names;
    private final int count;

    public RacingGameRequestDto(List<String> names, int count) {
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
