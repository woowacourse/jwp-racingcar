package racingcar.database;

import java.util.List;

public interface RacingGameDao {

    int insert(final int trialCount);

    List<Integer> selectGameIds();
}
