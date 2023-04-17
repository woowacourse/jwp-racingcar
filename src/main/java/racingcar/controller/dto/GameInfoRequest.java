package racingcar.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GameInfoRequest {

    private String names;
    private int count;

    private GameInfoRequest() {
    }

    public GameInfoRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    @NotBlank
    public String getNames() {
        return names;
    }

    @Min(1)
    public int getCount() {
        return count;
    }
}
