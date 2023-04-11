package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;

public interface CarDao {

    void save(Long gameId, CarDto carDto);

    void saveAll(Long racingGameId, List<CarDto> racingCars);
}
