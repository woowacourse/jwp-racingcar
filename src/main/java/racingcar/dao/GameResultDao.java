package racingcar.dao;

import java.util.List;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;
import racingcar.entity.PlayerRow;

public interface GameResultDao {
	void saveGame (final List<PlayerRow> playerRows, final TryCount tryCount);
	List<GameRow> fetchAllGameResult ();
	List<PlayerRow> fetchAllPlayerResultByGameId (Long gameId);
}
