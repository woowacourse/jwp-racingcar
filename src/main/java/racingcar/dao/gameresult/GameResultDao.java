package racingcar.dao.gameresult;

import racingcar.dto.db.GameResultDto;
import racingcar.dto.db.GameResultWithCarDto;

import java.util.List;
import java.util.Map;

public interface GameResultDao {

    Long save(final GameResultDto dto);

    Map<Long, List<GameResultWithCarDto>> findAll();
}
