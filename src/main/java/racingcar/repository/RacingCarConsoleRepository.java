package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RacingCarConsoleRepository implements RacingCarRepository {

    private static Long id = 1L;

    private final Map<Long, RacingGame> racingGameTable = new LinkedHashMap<>();

    @Override
    public void save(RacingGame racingGame, int trialCount) {
        racingGameTable.put(id++, racingGame);
    }

    @Override
    public List<RacingGame> findAll() {
        return new ArrayList<>(racingGameTable.values());
    }
}
