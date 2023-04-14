package racingcar.repository;

import java.util.List;
import racingcar.dto.CarResultDto;
import racingcar.dto.RacingGameResultDto;

public interface RacingCarRepository {

    void save(RacingGameResultDto racingGameResultDto);

    List<CarResultDto> findAll();
}
