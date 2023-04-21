package racingcar.dao;

import racingcar.controller.ApplicationType;
import racingcar.dto.GameFindDto;
import racingcar.entity.Game;

import java.util.List;

interface GameDao {

    Long save(final Game game);

    List<GameFindDto> findAll();
}
