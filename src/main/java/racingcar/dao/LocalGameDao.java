package racingcar.dao;

import racingcar.dto.GameIdDTO;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalGameDao implements GameDao {

    private final Map<Long, GameEntity> gameEntities = new HashMap<>();
    private Long id = 0L;

    @Override
    public Long insert(final int count) {
        final GameEntity gameEntity = new GameEntity(count);
        gameEntities.put(++id, gameEntity);
        return id;
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
        return gameEntities.keySet().stream()
                .map(GameIdDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private static class GameEntity {

        private final int trialCount;
        private final Timestamp createdAt;

        public GameEntity(final int trialCount) {
            this.trialCount = trialCount;
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }

        public int getTrialCount() {
            return trialCount;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }
    }
}
