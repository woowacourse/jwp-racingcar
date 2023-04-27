package racingcar.dao;

import racingcar.entity.GameResultEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryGameResultDao implements GameResultDao{

    private final List<Map<Long, GameResultEntity>> inMemory = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Long insert(GameResultEntity gameResultEntity) {
        id++;
        Map<Long,GameResultEntity> map = new HashMap<>();
        map.put(id,gameResultEntity);
        inMemory.add(map);
        return id;
    }

    @Override
    public List<Map<Long, GameResultEntity>> findAll() {
        return inMemory;
    }
}
