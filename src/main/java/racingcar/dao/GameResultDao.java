package racingcar.dao;

import racingcar.domain.GameResult;

import java.util.List;

public interface GameResultDao {
    int save(GameResult gameResult);

    GameResult findById(int id);

    List<GameResult> findAll();
}
