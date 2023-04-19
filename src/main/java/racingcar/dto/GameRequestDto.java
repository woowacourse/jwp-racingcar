package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GameRequestDto {
    @NotNull
    private final String names;
    @Positive
    private final int count;

    public GameRequestDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return Arrays.stream(names.split(","))
                .map(String::strip)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getCount() {
        return count;
    }
}
