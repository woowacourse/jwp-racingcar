package racingcar.dao;

import racingcar.entity.Player;
import racingcar.dto.RacingGameFindDto;

import java.util.List;

public interface RacingGameRepository {

    Long save(final int trialCount, final List<Player> players);

    List<RacingGameFindDto> findAll();
}
