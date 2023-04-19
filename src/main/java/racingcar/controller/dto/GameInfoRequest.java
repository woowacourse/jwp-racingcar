package racingcar.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GameInfoRequest {

    @NotBlank
    private String names;

    @Min(1)
    private int count;

    private GameInfoRequest() {
    }

    public GameInfoRequest(String names, int count) {
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
