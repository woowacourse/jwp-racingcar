package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarDao {
    void save(List<CarDto> carDtos);
}
