package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.TryCount;
import racingcar.entity.GameResult;
import racingcar.entity.PlayerResult;

public final class GameResultInMemoryDao implements GameResultDao {
	private final Map<Long, GameResult> gameData;
	private final Map<Long, PlayerResult> playerResultData;

	public GameResultInMemoryDao () {
		this.gameData = new HashMap<>();
		this.playerResultData = new HashMap<>();
	}

	@Override
	public void saveGame (final List<PlayerResult> playerResults, final TryCount tryCount) {
		Long id = (long) gameData.size();
		gameData.put(id, new GameResult(id, tryCount.getTryCount(), LocalDateTime.now()));
		for (final PlayerResult playerResult : playerResults) {
			playerResultData.put((long) playerResults.size(),
					new PlayerResult((long) playerResults.size(), id, playerResult.getName(),
							playerResult.getPosition(),
							playerResult.isWinner()));
		}
	}

	@Override
	public List<GameResult> fetchAllGameResult () {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<PlayerResult> fetchAllPlayerResultByGameId (final Long gameId) {
		throw new UnsupportedOperationException();
	}
}
