package racingcar.repository;

import racingcar.domain.Cars;

public interface PlayerRepository {
    boolean save(final Cars cars, final int racingGameId);
}
