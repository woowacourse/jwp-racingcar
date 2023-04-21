package racingcar.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao {
    int insert(String winners, Integer count);

    List<Integer> findAllIds();

    String findWinners(int gameId);
}
