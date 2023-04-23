package racingcar.dao;

import java.util.List;
import racingcar.dto.RacingGameDto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public interface RacingGameDao {

    RacingGameEntity insertRacingGame(final RacingGameDto racingGameDto);

    List<RacingGameEntity> selectAllResults();
}
