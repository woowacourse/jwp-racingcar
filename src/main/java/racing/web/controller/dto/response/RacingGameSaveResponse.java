package racing.web.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RacingGameSaveResponse {

    private final Long gameId;

    @JsonCreator
    public RacingGameSaveResponse(@JsonProperty("gameId") Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return gameId;
    }
}
