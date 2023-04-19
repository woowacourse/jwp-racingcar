package racingcar.domain;

import java.util.List;
import java.util.Optional;

public interface RacingGameRepository {

    Long save(final RacingGame racingGame);

    Optional<RacingGame> findById(final Long id);

    List<RacingGame> findAll();
}
