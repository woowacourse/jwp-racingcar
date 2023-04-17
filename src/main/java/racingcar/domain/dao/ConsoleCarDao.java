package racingcar.domain.dao;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.dao.entity.CarEntity;

public class ConsoleCarDao implements CarDao {

    @Override
    public void saveAll(Long raceResultId, List<Car> cars) {

    }

    @Override
    public List<CarEntity> findAll(Long resultId) {
        return null;
    }
}
