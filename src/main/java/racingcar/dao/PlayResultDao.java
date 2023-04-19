package racingcar.dao;

import java.util.List;
import racingcar.dto.PlayResultDto;

public interface PlayResultDao {
    Long insertWithKeyHolder(int trialCount, List<String> winners);

    List<PlayResultDto> selectAll();
}
