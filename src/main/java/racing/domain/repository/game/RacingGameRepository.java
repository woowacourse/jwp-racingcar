package racing.domain.repository.game;

import java.util.List;
import racing.domain.RacingCarGame;

public interface RacingGameRepository {
    Long saveGameByCount(int count);

    List<RacingCarGame> findAllGamesOrderByRecent();

    RacingCarGame findRacingGameById(Long gameId);
}
