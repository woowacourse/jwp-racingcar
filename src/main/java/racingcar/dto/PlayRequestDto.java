package racingcar.dto;

import javax.validation.constraints.NotNull;

public class PlayRequestDto {
    @NotNull
    private final String names;
    @NotNull
    private final Integer count;

    public PlayRequestDto(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
