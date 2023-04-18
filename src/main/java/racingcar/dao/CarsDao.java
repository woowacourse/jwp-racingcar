package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarsDao {
    int insert(int gameId, String name, int position);

    CarDto findById(int id);

    List<CarDto> findAllByGameId(int gameId);
}
