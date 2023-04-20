package racing.web.controller.dto.response;

public class RacingGameSaveResponse {
    private Long gameId;

    public RacingGameSaveResponse() {
    }

    public RacingGameSaveResponse(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return gameId;
    }
}
