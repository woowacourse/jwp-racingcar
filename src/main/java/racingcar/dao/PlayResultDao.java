package racingcar.dao;

import racingcar.entity.PlayResult;

import java.util.List;

public interface PlayResultDao {

    long insert(final String winners, final int trialCount);
    List<PlayResult> findAllPlayResult();

}
