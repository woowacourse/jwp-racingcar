package racingcar.dao;

import java.util.List;
import java.util.Map;
import racingcar.dao.dto.GameFinishedCarDto;
import racingcar.model.Car;

public interface CarDao {
    void saveAll(int gameId, List<Car> cars, List<String> winners);

    Map<Integer, List<GameFinishedCarDto>> selectAll();
}
