package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarRequest {
    @NotBlank
    private final String names;

    @Min(1)
    private final int count;

    public RacingCarRequest(String names, int count) {
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
