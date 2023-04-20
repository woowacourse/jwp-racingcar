package racingcar.dao;

import java.util.List;
import racingcar.entity.Game;

public interface GameDao {

    Long insert(Game game);

    List<Game> findAll();
}
