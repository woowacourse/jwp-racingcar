package racingcar.dao;

import racingcar.dao.entity.GameResultEntity;
import racingcar.dto.GameResultDto;

import java.util.List;

public interface GameResultDAO {
    int save(GameResultDto resultDto);

    List<GameResultEntity> findAll();
}
