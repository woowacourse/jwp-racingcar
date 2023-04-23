package racingcar.dao;

import racingcar.domain.Winner;

import java.util.List;

public interface WinnersDao {

    List<Winner> getWinnerNamesByGameId(int gameId);

    void saveWinners(int gameId, int carId);

}
