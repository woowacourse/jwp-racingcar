package racingcar.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PlayRequest {

    @NotBlank
    private final String names;

    @Min(1)
    private final int count;

    public PlayRequest(final String names, final int count) {
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
