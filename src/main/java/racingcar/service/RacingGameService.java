package racingcar.service;

import racingcar.dto.GameResultDto;

import java.util.List;

public interface RacingGameService {

    GameResultDto play(final List<String> names, final int gameTime);

    List<GameResultDto> findAllResult();
}
