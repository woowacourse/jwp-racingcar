package racingcar.repository.dao;

import java.util.List;
import racingcar.repository.entity.GameWinUsersEntity;

public interface GameWinUsersDao {

    long save(GameWinUsersEntity gameWinUsersEntity);

    List<GameWinUsersEntity> findByGameId(final long gameId);
}
