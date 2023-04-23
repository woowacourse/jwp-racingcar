package racingcar.dao.playerresult;

import java.util.List;
import racingcar.entity.PlayerRow;

public interface PlayerRowDao {
	void savePlayerRows (final List<PlayerRow> playerRows, final Long gameId);
	List<PlayerRow> fetchAllPlayerRowByGameId (Long gameId);
}
