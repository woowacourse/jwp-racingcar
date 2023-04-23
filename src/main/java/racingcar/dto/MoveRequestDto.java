package racingcar.dto;

import java.util.List;

public class MoveRequestDto {

    private final List<String> names;
    private final Integer count;

    public MoveRequestDto(List<String> names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
