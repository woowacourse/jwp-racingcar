package racingcar.repository;

import java.util.List;

import racingcar.repository.entity.Record;
import racingcar.service.RacingCarResult;

public interface RacingCarRepository {

    List<Record> findAllRecords();

    void saveRacingCarResult(final RacingCarResult racingCarResult);

}
