package racingcar.dao;

import racingcar.domain.GameResult;

import java.util.List;

public class DummyGameResultDao implements GameResultDao {
    @Override
    public long save(GameResult gameResult) {
        return 0;
    }

    @Override
    public GameResult findById(long id) {
        return null;
    }

    @Override
    public List<GameResult> findAll() {
        return null;
    }
}
