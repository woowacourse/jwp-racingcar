package racingcar.dao;

import racingcar.domain.Car;
import racingcar.entity.PlayResultEntity;

import java.util.List;

public interface PlayResultDAO {
    int returnTableIdAfterInsert(Integer count, List<Car> winners);
    List<PlayResultEntity> findAll();
}
