package racingcar.dao;

import java.util.List;
import racingcar.domain.TryCount;

public interface GameDao {
    Number insertGame(TryCount tryCount);

    List<Integer> selectGameIds();
}
