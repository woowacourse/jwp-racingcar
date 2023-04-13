package racingcar.dao;

import racingcar.dto.ResultDto;

public interface CarDao {
    void insertCar(ResultDto resultDto, long gameId);
}
