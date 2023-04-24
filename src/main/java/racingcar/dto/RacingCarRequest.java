package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import racingcar.controller.annotation.ContainDelimiter;

@Validated
public final class RacingCarRequest {

    @NotBlank
    @ContainDelimiter
    private final String names;
    @NotNull
    private final int tryCount;

    public RacingCarRequest(final String names, final int count) {
        this.names = names;
        this.tryCount = count;
    }

    public String getNames() {
        return names;
    }

    public int getTryCount() {
        return tryCount;
    }
}
