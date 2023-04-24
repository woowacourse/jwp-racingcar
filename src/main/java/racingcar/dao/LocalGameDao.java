package racingcar.dao;

import racingcar.dao.dto.GameIdDTO;

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
        id++;
        gameEntities.put(id, new GameEntity(id, count));
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

        private final Long id;
        private final int trialCount;
        private final Timestamp createdAt;

        public GameEntity(final Long id, final int trialCount) {
            this.id = id;
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
