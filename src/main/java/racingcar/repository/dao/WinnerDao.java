package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.WinnerEntity;

public interface WinnerDao {

    long save(WinnerEntity winnerEntity);

    List<WinnerEntity> findByGameId(final long gameId);
}
