package racingcar.dao;

import java.util.List;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

public interface CarDao {
    void saveAll(List<RacingCarResultDto> racingCarResultDtos);
    List<RacingCarDto> findCarsById(long gameId);
}
