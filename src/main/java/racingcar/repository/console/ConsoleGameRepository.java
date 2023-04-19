package racingcar.repository.console;

import racingcar.entity.GameEntity;
import racingcar.repository.GameRepository;

import java.util.List;

public class ConsoleGameRepository implements GameRepository {
    @Override
    public GameEntity save(GameEntity entity) {
        return null;
    }

    @Override
    public GameEntity findById(long id) {
        return null;
    }

    @Override
    public List<GameEntity> findAll() {
        return null;
    }
}
