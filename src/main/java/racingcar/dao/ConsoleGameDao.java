package racingcar.dao;

import racingcar.dao.entity.GameEntity;

public class ConsoleGameDao implements GameDao{

    @Override
    public Long insert(final GameEntity gameEntity) {
        return null;
    }

    @Override
    public int countGames() {
        return 0;
    }
}
