package racingcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racingcar.service.RacingResult;

public class InMemoryGameDao implements GameDao {
    private static final Map<Integer, RacingResult> store = new HashMap<>();
    private static int pointer = 0;

    @Override
    public RacingResult insertRacingResult(RacingResult racingResult) {
        racingResult.setId(++pointer);
        store.put(racingResult.getId(), racingResult);
        return racingResult;
    }

    @Override
    public List<RacingResult> selectAllResults() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
