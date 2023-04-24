package racingcar.database;

import racingcar.model.Car;

import java.util.List;

public interface RacingCarDao {

    int insert(final Car car, final int gameId, final boolean isWinner);

    List<String> selectWinners(final int gameId);

    List<Car> selectCarsBy(final int gameId);
}
