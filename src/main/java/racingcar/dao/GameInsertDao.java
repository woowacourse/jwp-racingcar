package racingcar.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface GameInsertDao {
    int insertGame(String winners, Integer count);
}
