package racingcar.dao;

import racingcar.vo.Trial;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RacingCollectionDao implements RacingDao {
    private final List<Racing> racings = new ArrayList<>();
    private int idHolder = 0;

    @Override
    public int saveRacing(Trial trial) {
        int id = idHolder++;
        racings.add(new Racing(id, trial.getValue(), Timestamp.valueOf(LocalDateTime.now())));
        return id;
    }
}
