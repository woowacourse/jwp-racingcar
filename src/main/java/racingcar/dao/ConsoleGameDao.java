package racingcar.dao;

import java.util.Optional;
import racingcar.entity.GameEntity;

public class ConsoleGameDao implements GameDao {
    @Override
    public Optional<Integer> saveAndGetId(final GameEntity game) {
        return Optional.of(0);
    }
}
