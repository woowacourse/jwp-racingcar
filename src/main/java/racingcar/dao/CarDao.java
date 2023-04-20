package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;

public interface CarDao {
    int insertCar(final CarDto carDto, final int gameId);
    List<CarDto> findCars(final int gameId);
    CarDto findCar(final String name, final int gameId);
}
