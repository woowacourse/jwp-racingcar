package racingcar.dao.gameresult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;

public final class GameRowInMemoryDao implements GameRowDao {
	private final Map<Long, GameRow> gameData;

	public GameRowInMemoryDao () {
		this.gameData = new HashMap<>();
	}

	@Override
	public Long saveGameRow (final TryCount tryCount) {
		Long id = (long) gameData.size();
		gameData.put(id, new GameRow(id, tryCount.getTryCount(), LocalDateTime.now()));

		return id;
	}

	@Override
	public List<GameRow> fetchAllGameRow () {
		throw new UnsupportedOperationException();
	}
}
