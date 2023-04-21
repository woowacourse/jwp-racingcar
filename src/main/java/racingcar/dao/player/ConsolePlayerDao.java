package racingcar.dao.player;

import java.util.Optional;
import racingcar.entity.PlayerEntity;

public class ConsolePlayerDao implements PlayerDao {

    @Override
    public Long save(final String name) {
        return null;
    }

    @Override
    public Optional<PlayerEntity> findByName(final String name) {
        return Optional.empty();
    }

    @Override
    public String findNameById(final Long id) {
        return null;
    }
}
