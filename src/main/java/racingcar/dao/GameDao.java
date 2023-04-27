package racingcar.dao;

import java.util.List;
import racingcar.dto.PlayResultDto;

public interface GameDao {
    Long insert(int trialCount, List<String> winners);

    List<PlayResultDto> selectAll();
}
