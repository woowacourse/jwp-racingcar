package racingcar.repository;

import racingcar.domain.entity.Race;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RaceCollectionRepository implements RaceRepository {
    private final List<Race> races = new ArrayList<>();
    private int idHolder = 0;

    @Override
    public Optional<Integer> saveRace(Race race) {
        int id = idHolder++;
        races.add(new Race(id, race.getTrialCount(), Timestamp.valueOf(LocalDateTime.now())));
        if (id < 0) {
            return Optional.empty();
        }
        return Optional.of(id);
    }

    @Override
    public List<Integer> findAllId() {
        return races.stream()
                .map(Race::getId)
                .collect(Collectors.toList());
    }
}
