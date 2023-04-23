package racingcar.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.entity.RacingGameEntity;

public class ConsoleRacingGameDao implements RacingGameDao {

    private final Map<Integer, RacingGameEntity> db = new HashMap<>();
    private int id = 1;

    @Override
    public int save(int count) {
        db.put(id, new RacingGameEntity(id, count, LocalDateTime.now()));
        return id++;
    }

    @Override
    public List<RacingGameEntity> findAll() {
        return new ArrayList<>(db.values());
    }
}
