package racingcar.dao;

import racingcar.dao.entity.WinnerEntity;

import java.util.List;

public interface WinnerRepository {
    void saveAll(List<WinnerEntity> winners);

    List<Integer> findWinnerCarIdsByGameId(final int gameId);
}
