package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;

public interface CarDao {

    void save(Long gameId, CarDto carDto);

    void saveAll(Long gameId, List<CarDto> racingCars);

    List<CarDto> findByGameId(Long gameId);
}
