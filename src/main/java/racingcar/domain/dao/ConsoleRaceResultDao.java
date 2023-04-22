package racingcar.domain.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.dao.entity.RaceResultEntity;

public class ConsoleRaceResultDao implements RaceResultDao {

    private final Map<Long, RaceResultEntity> raceResultStorage;
    private Long idCounter = 1L;

    public ConsoleRaceResultDao(final Map<Long, RaceResultEntity> raceResultStorage) {
        this.raceResultStorage = raceResultStorage;
    }

    @Override
    public Long save(final int trialCount, final String winners) {
        final Long id = getId();
        raceResultStorage.put(id, new RaceResultEntity(id, trialCount, winners, new ArrayList<>()));
        return id;
    }

    private synchronized Long getId() {
        return idCounter++;
    }

    @Override
    public List<RaceResultEntity> findAll() {
        return raceResultStorage.values()
            .stream()
            .collect(Collectors.toUnmodifiableList());
    }
}
