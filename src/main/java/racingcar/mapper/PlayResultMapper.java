package racingcar.mapper;

import racingcar.entity.PlayResultEntity;

import java.util.List;

public interface PlayResultMapper {
    long save(PlayResultEntity entity);

    PlayResultEntity findById(long id);

    List<PlayResultEntity> findAll();
}
