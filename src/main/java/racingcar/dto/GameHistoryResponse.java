package racingcar.dto;

import java.util.List;

public class GameHistoryResponse {

    private final List<GameResponse> history;

    public GameHistoryResponse(final List<GameResponse> history) {
        this.history = history;
    }

    public List<GameResponse> getHistory() {
        return history;
    }
}
