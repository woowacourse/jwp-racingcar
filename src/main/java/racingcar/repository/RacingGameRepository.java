package racingcar.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.RacingGame;
import racingcar.utils.DtoMapper;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(final GameResultDao gameResultDao, final CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    @Transactional
    public void save(final RacingGame racingGame) {
        final Long gameId = gameResultDao.save(DtoMapper.toRacingGameDto(racingGame));
        carDao.saveAll(DtoMapper.toCarsDto(gameId, racingGame));
    }
}
