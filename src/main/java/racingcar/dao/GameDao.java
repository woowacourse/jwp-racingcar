package racingcar.dao;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameDao {
    int insert(String winners, Integer count);

    Optional<Integer> findLastId();

    String findWinners(int gameId);
}
