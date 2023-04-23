package racingcar.dao;

import racingcar.dao.entity.GameEntity;

import java.util.List;

public interface GameRepository {

    int save(GameEntity gameEntity);

    List<Integer> findGameIds();
}
