package racingcar.dao;

import racingcar.dto.GameFindDto;

import java.util.List;

interface GameDao {

    Long save(final int trialCount);

    List<GameFindDto> findAll();
}
