package racingcar.dao.game;

import racingcar.dto.GameDto;

import java.util.List;

public interface GameDao {
    
    long save(GameDto gameDto);
    
    List<Long> findAllId();
}
