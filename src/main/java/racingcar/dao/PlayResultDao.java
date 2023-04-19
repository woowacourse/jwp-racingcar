package racingcar.dao;

import racingcar.domain.PlayResult;

import java.util.List;

public interface PlayResultDao {
    int save(PlayResult playResult);

    PlayResult findById(int id);

    List<PlayResult> findAll();
}
