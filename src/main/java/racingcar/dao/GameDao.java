package racingcar.dao;

import java.util.List;
import racingcar.controller.dto.GamePlayResponseDto;

public interface GameDao {
    int save(int trialCount, String winners);

    List<GamePlayResponseDto> selectAll();
}
