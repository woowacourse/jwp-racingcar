package racingcar.dao;

import java.util.List;
import racingcar.entity.GameEntity;

public class GameConsoleDao implements GameDao {

    private final int gameId = 1;
    private int trialCount;

    @Override
    public int insertGame(final int tryTimes) {
        this.trialCount = tryTimes;
        return gameId;
    }

    @Override
    public List<GameEntity> findAll() {
        return List.of(GameEntity.of(gameId, trialCount));
    }
}
