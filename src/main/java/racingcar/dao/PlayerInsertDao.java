package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.util.List;

@Repository
public interface PlayerInsertDao {
    void insertPlayers(int gameId, List<Car> cars);
}
