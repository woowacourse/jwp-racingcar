package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import racingcar.service.CarEntity;

public class InMemoryCarDao implements CarDao {
    private static final Map<Integer, CarEntity> store = new HashMap<>();
    private static int pointer = 0;

    @Override
    public void insertPlayer(CarEntity carEntity) {
        carEntity.setId(++pointer);
        store.put(carEntity.getId(), carEntity);
    }

    @Override
    public List<CarEntity> selectPlayerResultByRacingResultId(int ragingResultId) {
        return store.values()
            .stream()
            .filter(carEntity ->
                carEntity.getPlayResultId() == ragingResultId)
            .collect(Collectors.toList());
    }

    public void clearStore() {
        store.clear();
    }
}
