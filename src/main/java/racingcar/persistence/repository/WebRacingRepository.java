package racingcar.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.persistence.dao.RacingDao;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

@Repository
public class WebRacingRepository implements RacingRepository {
    private final RacingDao racingDao;

    public WebRacingRepository(RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    public void saveGameResult(final RacingGame racingGame, final int trialCount) {
        GameResultEntity gameResultEntityAfterSave = this.racingDao.saveGameResult(
                GameResultEntity.createToSave(racingGame, trialCount)
        );
        List<PlayerResultEntity> playerResultEntities = new ArrayList<>();
        for (Car car : racingGame.getCars()) {
            playerResultEntities.add(PlayerResultEntity.createToSave(
                    car,
                    gameResultEntityAfterSave.getId())
            );
        }
        this.racingDao.savePlayerResults(playerResultEntities);
    }
}
