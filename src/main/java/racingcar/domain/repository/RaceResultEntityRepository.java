package racingcar.domain.repository;

import java.util.List;
import racingcar.domain.dao.entity.RaceResultEntity;

public interface RaceResultEntityRepository {

    void save(final RaceResultEntity raceResultEntity);

    List<RaceResultEntity> findAll();
}
