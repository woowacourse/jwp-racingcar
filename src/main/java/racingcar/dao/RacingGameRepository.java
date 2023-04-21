package racingcar.dao;

import racingcar.controller.ApplicationType;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.dto.RacingGameFindDto;

import java.util.List;

public interface RacingGameRepository {

    Long save(final Game game, final List<Player> players);

    List<RacingGameFindDto> findAll();
}
