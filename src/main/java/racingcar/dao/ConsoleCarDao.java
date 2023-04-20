package racingcar.dao;

import racingcar.dao.entity.CarEntity;
import racingcar.model.Car;

import java.util.List;

public class ConsoleCarDao implements CarDao{
    @Override
    public void saveAll(int gameId, List<Car> cars) {
    }

    @Override
    public List<CarEntity> findAllById(int gameId) {
        return List.of();
    }
}
