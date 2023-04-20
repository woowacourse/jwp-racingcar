package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.entity.GameEntity;

public class GameInMemoryDao implements GameDao {

    private final List<GameEntity> gameEntities;

    public GameInMemoryDao() {
        gameEntities = new ArrayList<>();
    }

    @Override
    public Long insert(GameEntity gameEntity) {
        gameEntities.add(gameEntity);
        return (long) gameEntities.size();
    }

    @Override
    public List<GameEntity> findAll() {
        return new ArrayList<>(gameEntities);
    }
}
