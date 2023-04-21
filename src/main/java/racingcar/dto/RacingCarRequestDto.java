package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarRequestDto {
    @NotBlank
    private final String names;

    @Min(1)
    private final String count;

    public RacingCarRequestDto(final String names, final String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }

}
