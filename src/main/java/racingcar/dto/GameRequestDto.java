package racingcar.dto;

import java.util.List;

public class GameRequestDto {
    private final List<String> names;
    private final int count;

    public GameRequestDto(final List<String> names, final int count) {
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
