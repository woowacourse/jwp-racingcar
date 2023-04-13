package racingcar.dao;

import java.util.List;

public interface RacingGameDao {

    Long save(List<String> winners, int trialCount);
}
