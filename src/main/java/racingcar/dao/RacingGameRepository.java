package racingcar.dao;

import racingcar.dto.RacingGameFindDto;
import racingcar.entity.Game;
import racingcar.entity.Player;

import java.util.List;

public interface RacingGameRepository {

    Long save(final Game game, final List<Player> players);

    List<RacingGameFindDto> findAll();
}
