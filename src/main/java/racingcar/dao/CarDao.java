package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarDao {

    void save(int gameId, List<CarDto> carDtos);
}
