package racingcar.persistence.repository;

import racingcar.domain.RacingGame;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {

    private final List<RacingGame> gameResults;

    public InMemoryGameRepository() {
        this(new ArrayList<>());
    }

    public InMemoryGameRepository(final List<RacingGame> gameResults) {
        this.gameResults = gameResults;
    }

    @Override
    public void saveGame(final RacingGame racingGame) {
        gameResults.add(racingGame);
    }

    @Override
    public List<RacingGame> selectAllGames() {
        return List.copyOf(gameResults);
    }
}
