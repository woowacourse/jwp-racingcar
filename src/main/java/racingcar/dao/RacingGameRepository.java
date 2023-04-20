package racingcar.dao;

import racingcar.dto.PlayerSaveDto;
import racingcar.dto.RacingGameFindDto;

import java.util.List;

public interface RacingGameRepository {

    Long save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos);

    List<RacingGameFindDto> findAll();
}
