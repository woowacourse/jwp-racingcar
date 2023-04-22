package racingcar.db;

import racingcar.domain.Car;
import racingcar.domain.TryCount;
import racingcar.dto.GameWinnerDto;

import java.util.List;

public interface GameResultDao {

    int save(TryCount tryCount, String winners, List<Car> cars);

    List<GameWinnerDto> selectAllGameResult();

    void deleteAll();
}
