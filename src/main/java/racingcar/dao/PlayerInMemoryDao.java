package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.entity.PlayerEntity;

public class PlayerInMemoryDao implements PlayerDao {

    private final List<PlayerEntity> playerEntities;

    public PlayerInMemoryDao() {
        playerEntities = new ArrayList<>();
    }

    @Override
    public void insert(List<PlayerEntity> playerEntities) {
        this.playerEntities.addAll(playerEntities);
    }

    @Override
    public List<PlayerEntity> findAll() {
        return new ArrayList<>(playerEntities);
    }
}
