package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;

@Service
public class RacingGameManager {

    public RacingGame play(List<String> names, int tryCount) {
        RacingGame racingGame = createGame(names, tryCount);
        racingGame.run();
        return racingGame;
    }

    private RacingGame createGame(List<String> names, int tryCount) {
        return RacingGame.of(names, tryCount);
    }
}
