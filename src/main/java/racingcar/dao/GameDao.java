package racingcar.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface GameDao {
    int insert(String winners, Integer count);
}
