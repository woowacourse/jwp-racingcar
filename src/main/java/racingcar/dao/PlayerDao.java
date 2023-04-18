package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.util.List;

@Repository
public interface PlayerDao {
    void insert(int gameId, List<Car> cars);

    List<Car> find(int gameId);
}
