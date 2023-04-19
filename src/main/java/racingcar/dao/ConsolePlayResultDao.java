package racingcar.dao;

import racingcar.domain.PlayResult;

import java.util.List;

public class ConsolePlayResultDao implements PlayResultDao {
    @Override
    public int save(PlayResult playResult) {
        return 0;
    }

    @Override
    public PlayResult findById(int id) {
        return null;
    }

    @Override
    public List<PlayResult> findAll() {
        return null;
    }
}
