package racingcar.dto;

import java.util.List;

public class GameDto {

    private final List<String> names;
    private final int count;

    private GameDto(List<String> names, int count) {
        this.names = names;
        this.count = count;
    }

    public static GameDto of(final List<String> names, final int count) {
        return new GameDto(names, count);
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
