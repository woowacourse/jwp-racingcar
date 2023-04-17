package racingcar.repository;

import java.util.List;
import racingcar.domain.RacingGameResult;

public interface RacingGameRepository {

    RacingGameResult save(RacingGameResult racingGameResult);

    List<RacingGameResult> findAll();
}
