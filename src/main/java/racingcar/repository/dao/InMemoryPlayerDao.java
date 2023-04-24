package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import racingcar.repository.entity.PlayerEntity;

public class InMemoryPlayerDao implements PlayerDao {

    private final List<PlayerEntity> playerEntities = new ArrayList<>();

    @Override
    public long save(final PlayerEntity playerEntity) {
        final String name = playerEntity.getName();

        if (playerEntities.isEmpty()) {
            final Long id = 1L;
            playerEntities.add(new PlayerEntity(id, name));
            return id;
        }

        final int lastIndex = playerEntities.size() - 1;
        final Long id = playerEntities.get(lastIndex).getId() + 1;
        playerEntities.add(new PlayerEntity(id, name));
        return id;
    }

    @Override
    public PlayerEntity findById(final long id) {
        return playerEntities.stream()
                .filter(playerEntity -> playerEntity.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public PlayerEntity findByName(final String name) {
        return playerEntities.stream()
                .filter(playerEntity -> playerEntity.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
