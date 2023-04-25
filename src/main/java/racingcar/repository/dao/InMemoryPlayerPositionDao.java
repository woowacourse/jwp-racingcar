package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.repository.entity.PlayerPositionEntity;

public class InMemoryPlayerPositionDao implements PlayerPositionDao {

    private final List<PlayerPositionEntity> playerPositionEntities = new ArrayList<>();

    @Override
    public long save(final PlayerPositionEntity playerPositionEntity) {
        final Long gameId = playerPositionEntity.getGameId();
        final Long userId = playerPositionEntity.getUserId();
        final int position = playerPositionEntity.getPosition();

        if (playerPositionEntities.isEmpty()) {
            final Long id = 1L;
            playerPositionEntities.add(new PlayerPositionEntity(id, gameId, userId, position));
            return id;
        }

        final int lastIndex = playerPositionEntities.size() - 1;
        final Long id = playerPositionEntities.get(lastIndex).getId() + 1;
        playerPositionEntities.add(new PlayerPositionEntity(id, gameId, userId, position));
        return id;
    }

    @Override
    public List<PlayerPositionEntity> findByGameId(final long gameId) {
        return playerPositionEntities.stream()
                .filter(playerPositionEntity -> playerPositionEntity.getGameId() == gameId)
                .collect(Collectors.toList());
    }
}
