package racingcar.dao;

import racingcar.domain.Car;
import racingcar.entity.PlayerInfoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPlayerInfoDao implements PlayerInfoDAO {

    private int id = 0;
    private final Map<Integer, List<PlayerInfoEntity>> data;

    public InMemoryPlayerInfoDao() {
        data = new HashMap<>();
    }

    @Override
    public void insert(final int playResultId, final List<Car> cars) {
        final List<PlayerInfoEntity> playerInfoEntities = new ArrayList<>();
        for (Car car : cars) {
            playerInfoEntities.add(new PlayerInfoEntity(id++, car.getName(), car.getPosition(), playResultId));
        }
        data.put(playResultId, playerInfoEntities);
    }

    @Override
    public List<PlayerInfoEntity> findPlayerByPlayResultId(final int playResultId) {
        return data.get(playResultId);
    }
}
