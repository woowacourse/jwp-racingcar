package racingcar.dao;

import racingcar.dao.entity.PlayerResultEntity;
import racingcar.dto.PlayerResultDto;

import java.util.List;

public interface PlayerResultDAO {
    void saveAll(PlayerResultDto playerResultDto);

    List<PlayerResultEntity> findAll();
}
