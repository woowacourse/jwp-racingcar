package racingcar.dao;

import java.util.List;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.model.Car;

public interface CarDao {
    void saveAll(int gameId, List<Car> cars, List<String> winners);

    List<GamePlayResponseDto> selectAll();
}
