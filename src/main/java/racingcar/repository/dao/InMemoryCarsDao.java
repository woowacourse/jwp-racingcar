package racingcar.repository.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.repository.dao.entity.CarEntity;

public class InMemoryCarsDao implements CarsDao {

    private final Map<Long, List<CarEntity>> carsByPlayRecordId = new LinkedHashMap<>();

    @Override
    public void insert(final Long id, final List<CarEntity> cars) {
        carsByPlayRecordId.put(id, cars);
    }

    @Override
    public List<CarEntity> find(final Long playRecordId) {
        return carsByPlayRecordId.get(playRecordId);
    }

    @Override
    public Map<Long, List<CarEntity>> findAllCarsOrderByPlayCreatedAtDesc() {
        return new LinkedHashMap<>(carsByPlayRecordId);
    }
}
