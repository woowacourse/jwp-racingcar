package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;
import racingcar.entity.PlayerRow;

public final class GameResultInMemoryDao implements GameResultDao {
	private final Map<Long, GameRow> gameData;
	private final Map<Long, PlayerRow> playerResultData;

	public GameResultInMemoryDao () {
		this.gameData = new HashMap<>();
		this.playerResultData = new HashMap<>();
	}

	@Override
	public void saveGame (final List<PlayerRow> playerRows, final TryCount tryCount) {
		Long id = (long) gameData.size();
		gameData.put(id, new GameRow(id, tryCount.getTryCount(), LocalDateTime.now()));
		for (final PlayerRow playerRow : playerRows) {
			playerResultData.put((long) playerRows.size(),
					new PlayerRow((long) playerRows.size(), id, playerRow.getName(),
							playerRow.getPosition(),
							playerRow.isWinner()));
		}
	}

	@Override
	public List<GameRow> fetchAllGameResult () {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<PlayerRow> fetchAllPlayerResultByGameId (final Long gameId) {
		throw new UnsupportedOperationException();
	}
}
