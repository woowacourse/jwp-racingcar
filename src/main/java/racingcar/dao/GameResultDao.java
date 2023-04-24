package racingcar.dao;

import racingcar.domain.GameResult;

import java.util.List;

public interface GameResultDao {
    long save(GameResult gameResult);

    GameResult findById(long id);

    List<GameResult> findAll();
}
