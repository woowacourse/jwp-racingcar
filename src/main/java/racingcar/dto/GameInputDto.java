package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInputDto {

    private final String names;
    @JsonProperty(value = "count")
    private final int playCount;

    public GameInputDto(final String names, final int playCount) {
        this.names = names;
        this.playCount = playCount;
    }

    public String getNames() {
        return names;
    }

    public int getPlayCount() {
        return playCount;
    }

}
