package racingcar.repository;

import java.util.List;
import racingcar.dto.RacingGameDto;

public interface RacingGameRepository {

    RacingGameDto save(RacingGameDto racingGameDto);

    List<RacingGameDto> findAll();
}
