package racingcar.repository;

import java.util.Optional;

import racingcar.repository.mapper.RacingGameDto;

public interface RacingGameRepository {

    int save(final String winners, final int count);

    Optional<RacingGameDto> findById(final int id);
}
