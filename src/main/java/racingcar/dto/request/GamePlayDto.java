package racingcar.dto.request;

import java.util.List;

public class GamePlayDto {
    private final List<String> names;
    private final int count;

    public GamePlayDto(List<String> names, int count) {
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
