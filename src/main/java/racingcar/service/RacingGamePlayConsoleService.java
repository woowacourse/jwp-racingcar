package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

public class RacingGamePlayConsoleService implements RacingGamePlayService {
    private final RacingGameManager racingGameManager;

    public RacingGamePlayConsoleService(RacingGameManager racingGameManager) {
        this.racingGameManager = racingGameManager;
    }

    @Override
    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        RacingGame racingGame = racingGameManager.play(racingGameRequest.getNames(), racingGameRequest.getCount());
        return RacingGameResponse.from(racingGame);
    }
}
