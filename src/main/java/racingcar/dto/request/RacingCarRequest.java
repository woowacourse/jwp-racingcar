package racingcar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class RacingCarRequest {

    @NotNull
    private String names;

    @NotNull
    @PositiveOrZero
    private Integer count;

    public RacingCarRequest() {

    }

    public RacingCarRequest(final String names, final Integer count) {
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
