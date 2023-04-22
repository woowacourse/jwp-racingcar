package racingcar.dao.gameresult;

import java.util.List;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;
import racingcar.entity.PlayerRow;

public interface GameRowDao {
	void saveGame (final List<PlayerRow> playerRows, final TryCount tryCount);
	List<GameRow> fetchAllGameRow ();
	List<PlayerRow> fetchAllPlayerResultByGameId (Long gameId);
}
