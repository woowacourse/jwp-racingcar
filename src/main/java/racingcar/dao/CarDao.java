package racingcar.dao;

import java.util.List;

import racingcar.dto.CarDto;

public interface CarDao {
    void insertCar(List<CarDto> carDtos, long gameId);
}
