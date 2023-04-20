package racingcar.repository;

import racingcar.domain.Cars;
import racingcar.dto.PlayerDto;

import java.util.List;

public interface PlayerRepository {
    boolean save(final Cars cars, final int racingGameId);

    List<PlayerDto> findByRacingGameId(final int racingGameId);
}
