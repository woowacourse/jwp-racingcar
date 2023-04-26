package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.WinnerDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RacingGameRepository {

    Long save(final RacingGame racingGame);

    Optional<RacingGameDto> findById(final Long id);

    Map<Long, List<CarDto>> findAllCars();

    Map<Long, List<WinnerDto>> findAllWinners();
}
