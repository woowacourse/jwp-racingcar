package racingcar.dao;

import racingcar.dao.entity.GameEntity;

import java.util.List;

public class ConsoleGameDao implements GameDao{
    @Override
    public int save(int trialCount) {
        return 0;
    }

    @Override
    public List<GameEntity> findAll() {
        return List.of();
    }
}
