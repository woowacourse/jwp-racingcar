package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.GameResultDao;
import racingcar.domain.RacingGame;
import racingcar.utils.DtoMapper;

@Repository
public class GameResultRepository {

    private final GameResultDao gameResultDao;

    public GameResultRepository(final GameResultDao gameResultDao) {
        this.gameResultDao = gameResultDao;
    }

    public Long save(final RacingGame racingGame) {
        return gameResultDao.save(DtoMapper.toRacingGameDto(racingGame));
    }

}
