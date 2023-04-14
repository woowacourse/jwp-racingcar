package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

@Repository
public interface RacingGameDao {

    int saveResult(GameResultDto resultDto);

}
