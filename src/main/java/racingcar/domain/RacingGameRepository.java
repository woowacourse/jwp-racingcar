package racingcar.domain;

import java.util.Optional;

public interface RacingGameRepository {

    Long save(final RacingGame racingGame);

    Optional<RacingGame> findById(final Long id);
}
