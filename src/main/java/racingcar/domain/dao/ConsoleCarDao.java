package racingcar.domain.dao;

import java.util.List;
import java.util.Map;
import racingcar.domain.dao.entity.CarEntity;

public class ConsoleCarDao implements CarDao {

    private final Map<Long, List<CarEntity>> carEntityStorage;

    public ConsoleCarDao(final Map<Long, List<CarEntity>> carEntityStorage) {
        this.carEntityStorage = carEntityStorage;
    }

    @Override
    public void saveAll(final Long raceResultId, final List<CarEntity> carEntities) {
        if (carEntityStorage.containsKey(raceResultId)) {
            carEntityStorage.get(raceResultId).addAll(carEntities);
            return;
        }
        carEntityStorage.put(raceResultId, List.copyOf(carEntities));
    }

    @Override
    public List<CarEntity> findAll(final Long raceResultId) {
        return carEntityStorage.get(raceResultId);
    }
}
