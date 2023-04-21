package racingcar.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import racingcar.entity.GameEntity;

public class InMemoryGameDao implements GameDao {
    private static final Map<Integer, GameEntity> store = new LinkedHashMap<>();
    private static int pointer = 0;

    @Override
    public GameEntity insertRacingResult(GameEntity gameEntity) {
        gameEntity.setId(++pointer);
        store.put(gameEntity.getId(), gameEntity);
        return gameEntity;
    }

    @Override
    public List<GameEntity> selectAllResults() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
        pointer = 0;
    }
}
