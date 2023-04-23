package racingcar.game.repository;

import java.util.List;
import racingcar.game.model.GameResult;

public interface GameDAO {
    
    int insert(final int count, final GameResult gameResult);
    
    GameResult find(final int gameId);
    
    List<GameResult> findAll();
}
