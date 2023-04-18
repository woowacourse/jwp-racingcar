package racing.dao.game;

import java.util.List;
import racing.domain.Game;

public interface GameDao {

    int insert(final Game game);

    List<Integer> getAllId();
}
