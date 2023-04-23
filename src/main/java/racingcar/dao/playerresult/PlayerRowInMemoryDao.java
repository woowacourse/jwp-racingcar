package racingcar.dao.playerresult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.entity.PlayerRow;

public final class PlayerRowInMemoryDao implements PlayerRowDao{

	private final Map<Long, PlayerRow> playerResultData;

	public PlayerRowInMemoryDao () {
		this.playerResultData = new HashMap<>();
	}

	@Override
	public void savePlayerRows (final List<PlayerRow> playerRows, final Long gameId) {
		for (final PlayerRow playerRow : playerRows)
			playerResultData.put((long) playerRows.size(),
					new PlayerRow((long) playerRows.size(), gameId, playerRow.getName(),
							playerRow.getPosition(),
							playerRow.isWinner()));
	}

	@Override
	public List<PlayerRow> fetchAllPlayerRowByGameId (final Long gameId) {
		return null;
	}
}
