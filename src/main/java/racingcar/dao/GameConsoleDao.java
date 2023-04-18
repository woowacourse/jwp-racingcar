package racingcar.dao;

import java.util.List;
import racingcar.dto.GameIdDto;

public class GameConsoleDao implements GameDao {

    @Override
    public int insertGame(int tryTimes) {
        return 1;
    }

    @Override
    public List<GameIdDto> findAll() {
        return null;
    }
}
