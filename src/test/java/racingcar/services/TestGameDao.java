package racingcar.services;

import java.util.List;

import racingcar.dao.game.GameDao;

public class TestGameDao implements GameDao {
    @Override
    public long saveGame(int trialCount) {
        return 1;
    }

    @Override
    public List<Long> getGameIds() {
        return List.of(1L);
    }

}
