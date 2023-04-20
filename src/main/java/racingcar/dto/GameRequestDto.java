package racingcar.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GameRequestDto {
    @NotNull
    private final List<String> names;
    @Positive
    private final int count;

    public GameRequestDto(List<String> names, int count) {
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
