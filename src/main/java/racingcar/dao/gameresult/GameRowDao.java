package racingcar.dao.gameresult;

import java.util.List;
import racingcar.domain.TryCount;
import racingcar.entity.GameRow;

public interface GameRowDao {
	Long saveGameRow (final TryCount tryCount);
	List<GameRow> fetchAllGameRow ();
}
