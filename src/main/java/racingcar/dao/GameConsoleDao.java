package racingcar.dao;

import java.util.List;
import racingcar.dto.GameIdDto;

public class GameConsoleDao implements GameDao {

    private final int gameId = 1;

    @Override
    public int insertGame(int tryTimes) {
        return gameId;
    }

    @Override
    public List<GameIdDto> findAll() {
        return List.of(GameIdDto.from(gameId));
    }
}
