package racingcar.dao;

import racingcar.entity.PlayResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayResultInMemoryDao implements PlayResultDao{

    private static Long id = 0L;
    private final Map<Long, PlayResult> memory = new HashMap<>();
    @Override
    public long insert() {
        memory.put(id, new PlayResult(id++));
        return id - 1;
    }

    @Override
    public List<PlayResult> findAllPlayResult() {
        return new ArrayList<>(memory.values());
    }
}
