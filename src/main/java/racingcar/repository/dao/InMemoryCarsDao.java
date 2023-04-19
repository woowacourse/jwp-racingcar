package racingcar.repository.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.repository.dao.entity.CarEntity;

public class InMemoryCarsDao implements CarsDao {

    private final Map<Long, List<CarEntity>> carsByPlayId = new LinkedHashMap<>();

    @Override
    public void insert(final long id, final List<CarEntity> cars) {
        carsByPlayId.put(id, cars);
    }

    @Override
    public List<CarEntity> find(final long id) {
        return carsByPlayId.get(id);
    }

    @Override
    public Map<Long, List<CarEntity>> findAllByPlayerId() {
        return new LinkedHashMap<>(carsByPlayId);
    }
}
