package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.List;

public class InMemoryCarDao implements CarDao {

    private List<CarEntity> carEntities;


    @Override
    public void insert(List<CarEntity> carEntities, long gameId) {
        this.carEntities = carEntities;
    }

    @Override
    public List<CarEntity> selectByGameId(long gameId) {
        return carEntities;
    }
}
