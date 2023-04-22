package racingcar.controller.dto;

import javax.validation.constraints.NotNull;

public class GamePlayRequest {

    @NotNull
    private final String names;
    @NotNull
    private final Integer count;

    public GamePlayRequest(final String names, final Integer count) {
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
