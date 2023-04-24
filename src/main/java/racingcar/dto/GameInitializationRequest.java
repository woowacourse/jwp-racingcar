package racingcar.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class GameInitializationRequest {

    @NotBlank
    private String names;
    @Max(100)
    private int count;

    public GameInitializationRequest(final String names, final int count) {
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
