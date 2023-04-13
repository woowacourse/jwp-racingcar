package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarDao {

    void save(Long gameId, CarDto carDto);

    void saveAll(Long gameId, List<CarDto> racingCars);

    List<CarDto> findCarsByGameId(Long gameId);
}
