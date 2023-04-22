package racingcar.dao;

import java.util.List;
import racingcar.domain.TryCount;
import racingcar.entity.GameResult;
import racingcar.entity.PlayerResult;

public interface GameResultDao {
	void saveGame (final List<PlayerResult> playerResults, final TryCount tryCount);
	List<GameResult> fetchAllGameResult ();
	List<PlayerResult> fetchAllPlayerResultByGameId (Long gameId);
}
