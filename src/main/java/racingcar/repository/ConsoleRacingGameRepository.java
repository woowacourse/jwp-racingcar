package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.game.RacingGame;

public class ConsoleRacingGameRepository implements RacingGameRepository {

    private final List<RacingGame> gameHistory = new ArrayList<>();

    @Override
    public RacingGame save(RacingGame racingGame, int trialCount) {
        racingGame.setId(gameHistory.size() + 1L);
        gameHistory.add(racingGame);
        return racingGame;
    }

    @Override
    public List<RacingGame> findAll() {
        return gameHistory;
    }
}
