package racingcar.dao;

import racingcar.domain.Car;

import java.util.List;

public class InMemoryPlayerDao implements PlayerDao {
    @Override
    public void insert(int gameId, List<Car> cars) {
        
    }

    @Override
    public List<Car> find(int gameId) {
        return null;
    }
}
