package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.GameWinnerEntity;

public interface GameWinnerDao {

    long save(GameWinnerEntity gameWinnerEntity);

    List<GameWinnerEntity> findByGameId(final long gameId);
}
