package racingcar.database;

import java.util.List;

public class EmptyRacingGameDao implements RacingGameDao {
    @Override
    public int insert(final int trialCount) {
        return 0;
    }

    @Override
    public List<Integer> selectGameIds() {
        return List.of();
    }
}
