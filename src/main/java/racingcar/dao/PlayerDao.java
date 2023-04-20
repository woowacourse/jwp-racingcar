package racingcar.dao;

import java.util.List;
import racingcar.entity.PlayerEntity;

public interface PlayerDao {

    void insert(List<PlayerEntity> playerEntities);

    List<PlayerEntity> findAll();
}
