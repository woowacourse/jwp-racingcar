package racingcar.dto;

import javax.validation.constraints.PositiveOrZero;

public class RequestDto {
    private final String names;
    @PositiveOrZero
    private final int count;

    public RequestDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
