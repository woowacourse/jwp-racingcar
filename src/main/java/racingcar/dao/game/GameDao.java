package racingcar.dao.game;

import racingcar.dto.GameDto;

import java.util.List;

public interface GameDao {
    
    long insert(GameDto gameDto);
    
    List<Long> findAllId();
    
    GameDto findById(long gameId);
}
