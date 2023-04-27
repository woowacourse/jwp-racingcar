package racingcar.dto;

import java.util.List;

public class GameInitializeDto {
    private List<String> names;
    private int count;

    public GameInitializeDto() {

    }

    public GameInitializeDto(List<String> names, int count) {
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
