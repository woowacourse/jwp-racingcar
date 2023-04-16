package racingcar.repository;

import racingcar.domain.entity.Race;

import java.util.List;

public interface RaceRepository {
    int saveRace(Race race);

    List<Integer> findAllId();
}
