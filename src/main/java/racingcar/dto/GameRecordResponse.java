package racingcar.dto;

import java.util.List;

public class GameRecordResponse {

    private final List<GameResultResponse> gameResultResponses;

    public GameRecordResponse(final List<GameResultResponse> gameResultResponses) {
        this.gameResultResponses = gameResultResponses;
    }

    public List<GameResultResponse> getGameResultResponses() {
        return gameResultResponses;
    }
}
