package racingcar.dao.game;

import java.util.List;
import racingcar.dao.entity.Game;
import racingcar.dto.RacingCarGameResultResponseDto;

public interface RacingCarGameDao {

    public Long insertGameWithKeyHolder(Game game);
    public List<RacingCarGameResultResponseDto> findAll();
}

