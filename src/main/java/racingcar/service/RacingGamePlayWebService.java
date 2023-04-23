package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGamePlayWebService implements RacingGamePlayService {
    private final RacingGameAddService racingGameAddService;
    private final RacingGameManager racingGameManager;

    public RacingGamePlayWebService(RacingGameAddService racingGameAddService, RacingGameManager racingGameManager) {
        this.racingGameAddService = racingGameAddService;
        this.racingGameManager = racingGameManager;
    }

    @Override
    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        RacingGame racingGame = racingGameManager.play(racingGameRequest.getNames(), racingGameRequest.getCount());
        racingGame.run();
        return racingGameAddService.addGame(racingGame, racingGameRequest.getCount());
    }
}
