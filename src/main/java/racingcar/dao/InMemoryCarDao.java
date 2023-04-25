package racingcar.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import racingcar.entity.CarEntity;

public class InMemoryCarDao implements CarDao {
    private static final Map<Integer, CarEntity> store = new LinkedHashMap<>();
    private static int pointer = 0;

    @Override
    public void insertCar(CarEntity carEntity) {
        carEntity.setId(++pointer);
        store.put(carEntity.getId(), carEntity);
    }

    @Override
    public List<CarEntity> selectCarsByGameId(int gameId) {
        return store.values()
            .stream()
            .filter(carEntity ->
                carEntity.getGameId() == gameId)
            .collect(Collectors.toList());
    }

    public void clearStore() {
        store.clear();
        pointer = 0;
    }
}
