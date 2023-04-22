package racingcar.repository;

import racingcar.repository.dto.RacingGameDto;

import java.util.List;
import java.util.Optional;

public interface RacingGameRepository {
    int save(final String winners, final int count);

    Optional<RacingGameDto> findById(final int id);

    List<RacingGameDto> findAll();
}
