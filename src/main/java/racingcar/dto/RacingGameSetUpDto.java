package racingcar.dto;

import java.util.List;

public class RacingGameSetUpDto {

    private final List<String> names;
    private final String count;

    public RacingGameSetUpDto(List<String> names, String count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
