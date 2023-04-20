package racingcar.db;

import racingcar.domain.Car;

import java.util.List;

public interface ResultCarDao {
    void save(int gameId, List<Car> carDtoList);

    List<Car> findByGameId(int gameId);
}
