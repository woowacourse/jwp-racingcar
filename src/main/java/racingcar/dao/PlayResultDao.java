package racingcar.dao;

import racingcar.entity.PlayResult;

import java.util.List;

public interface PlayResultDao {
    long insert();

    List<PlayResult> findAllPlayResult();

}
