package racingcar.db;

import racingcar.domain.Car;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultDto;

import java.util.List;

public interface RacingGameRepository {
    void saveGame(TryCount tryCount, String winners, List<Car> cars);

    List<GameResultDto> findAllGameResult();

    void deleteAll();
}
