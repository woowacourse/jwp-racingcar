package racingcar.dao;

import racingcar.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCarDao implements CarDao {

    private final List<CarEntity> inMemory = new ArrayList<>();

    @Override
    public void insert(List<CarEntity> carEntities) {
        for (CarEntity carEntity : carEntities) {
            inMemory.add(carEntity);
        }
    }

    @Override
    public List<CarEntity> findByGameResultId(Long gameResultId) {
        return inMemory;
    }
}
