package racingcar.mapper;

import racingcar.entity.PlayResultEntity;

import java.util.List;

public class ConsolePlayerResultMapper implements PlayResultMapper {
    @Override
    public long save(PlayResultEntity entity) {
        return 0;
    }

    @Override
    public PlayResultEntity findById(long id) {
        return null;
    }

    @Override
    public List<PlayResultEntity> findAll() {
        return null;
    }
}
