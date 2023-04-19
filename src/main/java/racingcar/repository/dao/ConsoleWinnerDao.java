package racingcar.repository.dao;

import java.util.Collections;
import java.util.List;

import racingcar.repository.entity.WinnerEntity;

public class ConsoleWinnerDao implements WinnerDao {

    @Override
    public long save(final WinnerEntity winnerEntity) {
        return 0;
    }

    @Override
    public List<WinnerEntity> findByGameId(final long gameId) {
        return Collections.emptyList();
    }
}
