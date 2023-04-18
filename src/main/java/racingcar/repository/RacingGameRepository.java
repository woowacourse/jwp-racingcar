package racingcar.repository;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;

public interface RacingGameRepository {

    List<GameHistoryEntity> findGameHistories();

    void saveGameResult(Cars cars, TryCount tryCount);

}
