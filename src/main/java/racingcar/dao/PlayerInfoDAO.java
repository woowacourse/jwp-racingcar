package racingcar.dao;

import racingcar.domain.Car;
import racingcar.entity.PlayerInfoEntity;

import java.util.List;

public interface PlayerInfoDAO {

    void insert(int playResultId, List<Car> cars);
    List<PlayerInfoEntity> findPlayerByPlayResultId(int playResultId);
}
