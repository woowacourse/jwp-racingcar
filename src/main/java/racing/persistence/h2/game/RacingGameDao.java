package racing.persistence.h2.game;

import java.util.List;

public interface RacingGameDao {

    Long saveGame(int count);

    List<RacingGameEntity> findAllGameByRecent();

    RacingGameEntity findGameById(Long gameId);
}
