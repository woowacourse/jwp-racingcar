package racingcar.dao.game;

import java.util.HashMap;
import java.util.Map;
import racingcar.model.TryCount;

public class InMemoryGameDao implements GameDao {

    private static final Map<Integer, GameEntity> GAME_CACHE = new HashMap<>();

    @Override
    public int save(final int tryCount) {
        final GameEntity game = new GameEntity(new TryCount(tryCount));
        final int gameId = game.getId();
        GAME_CACHE.put(gameId, game);

        return gameId;
    }
}
