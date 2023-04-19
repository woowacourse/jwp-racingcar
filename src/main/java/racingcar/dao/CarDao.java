package racingcar.dao;

import java.util.List;
import racingcar.dto.CarDto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public interface CarDao {

    void insertCar(final CarDto car, final int gameId);

    List<CarDto> findCarsByRacingGameId(final int gameId);
}
