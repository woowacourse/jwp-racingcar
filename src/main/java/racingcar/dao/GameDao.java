package racingcar.dao;

import java.util.List;

public interface GameDao {
    long save(int count);
    List<Long> findAllId();
}
