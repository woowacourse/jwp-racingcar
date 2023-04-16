package racingcar.dao;

import racingcar.entity.Race;

import java.util.List;

public interface RaceRepository {
    int saveRace(Race race);

    List<Integer> findAllId();
}
