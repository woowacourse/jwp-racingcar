package racingcar.game.interfaces;

import java.util.List;

public interface GameDAO {
    
    int insert(final int count, final GameResult gameResult);
    
    GameResult find(final int gameId);
    
    List<GameResult> findAll();
}
