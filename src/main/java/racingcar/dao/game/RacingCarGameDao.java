package racingcar.dao.game;

import java.util.List;
import racingcar.dao.entity.Game;
import racingcar.dto.ResultResponseDto;

public interface RacingCarGameDao {

    public Long insertGameWithKeyHolder(Game game);
    public List<ResultResponseDto> findAll();
}

