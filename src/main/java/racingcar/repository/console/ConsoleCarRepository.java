package racingcar.repository.console;

import racingcar.entity.CarEntity;
import racingcar.repository.CarRepository;

import java.util.List;

public class ConsoleCarRepository implements CarRepository {
    @Override
    public CarEntity save(CarEntity entity) {
        return null;
    }

    @Override
    public CarEntity findById(long id) {
        return null;
    }

    @Override
    public List<CarEntity> findAllByGameId(long id) {
        return null;
    }
}
