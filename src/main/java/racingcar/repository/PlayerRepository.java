package racingcar.repository;

import racingcar.domain.CarGroup;

public interface PlayerRepository {

    boolean save(final CarGroup carGroup, final int racingGameId);
}
