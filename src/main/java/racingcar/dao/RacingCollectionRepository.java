package racingcar.dao;

import racingcar.entity.Race;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCollectionRepository implements RaceRepository {
    private final List<Race> races = new ArrayList<>();
    private int idHolder = 0;

    @Override
    public int saveRace(Race race) {
        int id = idHolder++;
        races.add(new Race(id, race.getTrialCount(), Timestamp.valueOf(LocalDateTime.now())));
        return id;
    }

    @Override
    public List<Integer> findAllId() {
        return races.stream()
                .map(Race::getId)
                .collect(Collectors.toList());
    }
}
