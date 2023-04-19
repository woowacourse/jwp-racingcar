package racingcar.dao;

import java.util.List;
import racingcar.dto.RacingCarDto;

public interface RacingCarDao {
    void insert(Long gameId, String playerName, int playerPosition);

    List<RacingCarDto> selectByGameId(int gameId);
}
