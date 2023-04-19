package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRacingGameRepository implements RacingGames {

    private static final List<RacingGame> racingGames = new ArrayList<>();

    @Override
    public RacingGame save(RacingGame racingGame) {
        racingGames.add(racingGame);
        return racingGame;
    }

    @Override
    public List<RacingGame> getAllRacingGames() {
        return List.copyOf(racingGames);
    }
}
