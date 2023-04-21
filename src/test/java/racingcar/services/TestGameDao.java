package racingcar.services;

import java.util.List;

import racingcar.dao.entity.Game;
import racingcar.dao.game.GameDao;

public class TestGameDao implements GameDao {
    @Override
    public long saveGame(Game game) {
        return 1;
    }

    @Override
    public List<Long> getGameIds() {
        return List.of(1L);
    }
}
