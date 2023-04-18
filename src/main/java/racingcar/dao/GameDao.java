package racingcar.dao;

import java.util.List;
import racingcar.dto.GameIdDto;

public interface GameDao {
    int insertGame(final int tryTimes);
    List<GameIdDto> findAll();
}
