package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.repository.dao.entity.CarEntity;

public class InMemoryCarsDao implements CarsDao {

    private final Map<Long, List<CarEntity>> carsByPlayRecordId = new LinkedHashMap<>();

    @Override
    public void insert(final List<CarEntity> cars) {
        Long playRecordId = cars.get(0).getPlayRecordId();
        carsByPlayRecordId.put(playRecordId, cars);
    }

    @Override
    public List<CarEntity> find(final Long playRecordId) {
        return carsByPlayRecordId.get(playRecordId);
    }

    @Override
    public List<List<CarEntity>> findAllCarsOrderByPlayCreatedAtDesc() {
        List<List<CarEntity>> allCars = new ArrayList<>(carsByPlayRecordId.values());
        Collections.reverse(allCars);
        return allCars;
    }
}
