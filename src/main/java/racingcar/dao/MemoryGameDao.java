package racingcar.dao;

import java.util.ArrayList;
import java.util.List;

public class MemoryGameDao implements GameDao{
    private List<Long> ids = new ArrayList<>();
    private long id = 0L;

    @Override
    public long save(int count) {
        ids.add(id++);
        return id;
    }

    @Override
    public List<Long> findAllId() {
        return ids;
    }
}
