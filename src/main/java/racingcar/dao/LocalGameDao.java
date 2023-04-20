package racingcar.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalGameDao implements GameDao {

    private final List<GameEntity> gameEntities = new ArrayList<>();

    @Override
    public Long insert(final int count) {
        final GameEntity gameEntity = new GameEntity(count);
        gameEntities.add(gameEntity);
        return gameEntity.getId();
    }

    @Override
    public int countRows() {
        return gameEntities.size();
    }

    @Override
    public void deleteAll() {
        gameEntities.clear();
    }

    @Override
    public List<GameIdDTO> findAllGameIds() {
        return gameEntities.stream()
                .map(gameEntity -> new GameIdDTO(gameEntity.getId()))
                .collect(Collectors.toUnmodifiableList());
    }

    private class GameEntity {

        private final Long id;
        private final int trialCount;
        private final Timestamp createdAt;

        public GameEntity(final int trialCount) {
            this.id = (long) gameEntities.size();
            this.trialCount = trialCount;
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }

        public Long getId() {
            return id;
        }

        public int getTrialCount() {
            return trialCount;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }
    }
}
