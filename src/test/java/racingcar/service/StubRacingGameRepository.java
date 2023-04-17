package racingcar.service;

import java.util.HashMap;
import java.util.Map;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    private final Map<Integer, RacingGame> gameIdToRacingGame = new HashMap<>();
    private int currentId = 0;

    @Override
    public int save(final RacingGame racingGame) {
        gameIdToRacingGame.put(++currentId, racingGame);
        return currentId;
    }
}
