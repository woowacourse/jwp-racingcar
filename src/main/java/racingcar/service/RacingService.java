package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.persistence.RacingDao;
import racingcar.persistence.entity.GameResultEntity;

@Service
public final class RacingService {

    private final RacingDao racingDao;

    @Autowired
    public RacingService(final RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    public RacingGame playRacingGame(final String carNamesText, final int tryCount) {
        RacingGame racingGame = RacingGame.of(
                List.of(carNamesText.split(",")),
                tryCount);
        racingGame.play();
        save(racingGame, tryCount);
        return racingGame;
    }

    private void save(final RacingGame racingGame, final int trialCount) {
        GameResultEntity gameResultEntity = this.racingDao.saveGameResult(racingGame, trialCount);
        this.racingDao.savePlayerResults(racingGame, gameResultEntity.getId());
    }
}
