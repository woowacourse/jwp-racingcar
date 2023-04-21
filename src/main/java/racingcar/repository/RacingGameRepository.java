package racingcar.repository;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.entity.GameHistoryEntity;

public interface RacingGameRepository {

    List<GameHistoryEntity> findGameHistories();

    void saveGameResult(final Cars cars);
}
