package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleRacingCarRepository implements RacingCarRepository {

    private final Map<Integer, RacingGame> racingGameData;
    private int id;

    public ConsoleRacingCarRepository() {
        this.racingGameData = new HashMap<>();
        id = 1;
    }

    @Override
    public void saveRacingGame(RacingGame racingGame) {
        racingGameData.put(id++, racingGame);
    }

    @Override
    public List<RacingGame> findAllEndedRacingGame() {
        return new ArrayList<>(racingGameData.values());
    }
}
