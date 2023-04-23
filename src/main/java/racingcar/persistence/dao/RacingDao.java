package racingcar.persistence.dao;

import java.util.List;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

public interface RacingDao {

    GameResultEntity saveGameResult(final GameResultEntity gameResultEntity);

    void savePlayerResults(final List<PlayerResultEntity> playerResultEntities);
}
