package racingcar.controller.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class GameInfoRequest {

    @Size(min = 2)
    private String names;

    @Positive
    private int count;

    private GameInfoRequest() {
    }

    public GameInfoRequest(final String names, final int count) {
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
