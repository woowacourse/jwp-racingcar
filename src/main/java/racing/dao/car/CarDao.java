package racing.dao.car;

import java.util.List;
import racing.domain.Car;
import racing.dto.CarDto;

public interface CarDao {

    void insert(final Car car, final int gameId);

    List<CarDto> findByGameId(Integer gameId);
}
