package racingcar.dao;

import java.util.List;

import racingcar.service.CarEntity;

public interface CarDao {
    List<CarEntity> selectPlayerResultByRacingResultId(int ragingResultId);

    void insertPlayer(final CarEntity carEntity);
}
