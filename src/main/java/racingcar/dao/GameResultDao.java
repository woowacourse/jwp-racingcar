package racingcar.dao;

import racingcar.entity.GameResultEntity;

import java.util.List;
import java.util.Map;

public interface GameResultDao {
    Long insert(GameResultEntity gameResultEntity);

    List<Map<Long, GameResultEntity>> findAll();
}
