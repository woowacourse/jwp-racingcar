package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;

public interface CarDao {

    void saveAll(Long gameId, List<CarDto> racingCars);
}
