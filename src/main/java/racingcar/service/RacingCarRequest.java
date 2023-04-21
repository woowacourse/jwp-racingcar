package racingcar.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarRequest {
    @NotBlank
    private final String names;

    @Min(1)
    private final String count;

    public RacingCarRequest(final String names, final String count) {
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
