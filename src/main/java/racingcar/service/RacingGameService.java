package racingcar.service;

import racingcar.dto.GameResultDto;

import java.util.List;

public interface RacingGameService {

    public GameResultDto play(final List<String> names, final int gameTime);

}
