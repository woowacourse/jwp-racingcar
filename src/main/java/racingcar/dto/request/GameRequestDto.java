package racingcar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class GameRequestDto {

    @NotNull
    private String names;
    @Positive
    private int count;

    public GameRequestDto(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public GameRequestDto() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
