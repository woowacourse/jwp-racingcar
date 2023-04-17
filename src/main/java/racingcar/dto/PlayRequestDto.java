package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PlayRequestDto {

    @NotBlank
    private final String names;
    @Min(1)
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
