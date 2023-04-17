package racingcar.dao;

import racingcar.entity.GameEntity;

public class ConsoleGameDao implements GameDao {
    @Override
    public int saveAndGetId(final GameEntity game) {
        return 0;
    }
}
