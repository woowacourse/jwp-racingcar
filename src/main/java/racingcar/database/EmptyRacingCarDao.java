package racingcar.database;

import racingcar.model.Car;

import java.util.List;

public class EmptyRacingCarDao implements RacingCarDao {
    @Override
    public int insert(final Car car, final int gameId, final boolean isWinner) {
        return 0;
    }

    @Override
    public List<String> selectWinners(final int gameId) {
        return List.of();
    }

    @Override
    public List<Car> selectCarsBy(final int gameId) {
        return List.of();
    }
}
