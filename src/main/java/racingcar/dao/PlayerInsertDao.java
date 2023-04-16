package racingcar.dao;

import racingcar.domain.Car;

import java.util.List;

public interface PlayerInsertDao {
    void insertPlayers(int gameId, List<Car> cars);
}
