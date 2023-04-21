package racingcar.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.dao.entity.RacingGameEntity;

public class RacingGameInMemoryDao implements RacingGameDao {
    private final Map<Long, RacingGameEntity> database = new HashMap<>();
    private Long id = 1L;

    @Override
    public Long save(RacingGameEntity racingGameEntity) {
        database.put(id, racingGameEntity);
        id++;
        return id - 1;
    }

    @Override
    public List<RacingGameEntity> findAllByCreatedTimeAsc() {
        return database.values()
                .stream().sorted(Comparator.comparing(RacingGameEntity::getCreatedTime))
                .collect(Collectors.toList());
    }
}
