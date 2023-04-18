package racingcar.repository;

import racingcar.dto.GameResultResponse;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {

    private final List<GameResultResponse> gameResults;

    public InMemoryGameRepository() {
        this(new ArrayList<>());
    }

    public InMemoryGameRepository(final List<GameResultResponse> gameResults) {
        this.gameResults = gameResults;
    }

    @Override
    public void saveGameRecord(final GameResultResponse gameResultResponse, final int ignored) {
        gameResults.add(gameResultResponse);
    }

    @Override
    public List<GameResultResponse> makeGameRecords() {
        return List.copyOf(gameResults);
    }
}
