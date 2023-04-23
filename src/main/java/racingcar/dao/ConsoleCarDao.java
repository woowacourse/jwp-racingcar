package racingcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.entity.CarEntity;

public class ConsoleCarDao implements CarDao {

    private final Map<Integer, CarEntity> db = new HashMap<>();
    private int id = 1;

    @Override
    public void saveAll(int gameId, List<CarEntity> carEntities) {
        for (CarEntity carEntity : carEntities) {
            db.put(id, new CarEntity(id, gameId, carEntity.getName(), carEntity.getPosition(), carEntity.isWin()));
            id++;
        }
    }

    @Override
    public List<CarEntity> findAll() {
        return new ArrayList<>(db.values());
    }
}
