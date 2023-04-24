package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.repository.entity.GameEntity;

public class InMemoryGameDao implements GameDao {

    private final List<GameEntity> gameEntities = new ArrayList<>();

    @Override
    public long save(final GameEntity gameEntity) {
        final int trialCount = gameEntity.getTrialCount();
        final String lastModifiedTime = gameEntity.getLastModifiedTime();

        if (gameEntities.isEmpty()) {
            final Long id = 1L;
            gameEntities.add(new GameEntity(id, trialCount, lastModifiedTime));
            return id;
        }

        final int lastIndex = gameEntities.size() - 1;
        final Long id = gameEntities.get(lastIndex).getId() + 1;
        gameEntities.add(new GameEntity(id, trialCount, lastModifiedTime));
        return id;
    }

    @Override
    public List<GameEntity> findAll() {
        return gameEntities;
    }
}
