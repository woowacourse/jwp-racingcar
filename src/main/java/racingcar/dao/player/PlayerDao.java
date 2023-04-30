package racingcar.dao.player;

import java.util.Optional;
import racingcar.entity.PlayerEntity;

public interface PlayerDao {

    Long save(final String name);

    Optional<PlayerEntity> findByName(final String name);

    String findNameById(final Long id);
}
