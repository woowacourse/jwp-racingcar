package racingcar.repository;

import racingcar.domain.entity.Race;

import java.util.List;
import java.util.Optional;

public interface RaceRepository {
    Optional<Integer> saveRace(Race race);

    List<Integer> findAllId();
}
