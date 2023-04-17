package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.RacingGame;
import racingcar.dto.db.GameResultWithCarDto;
import racingcar.utils.DtoMapper;

import java.util.List;
import java.util.Map;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(final GameResultDao gameResultDao, final CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    public void save(final RacingGame racingGame) {
        final Long gameId = gameResultDao.save(DtoMapper.toRacingGameDto(racingGame));
        carDao.saveAll(DtoMapper.toCarsDto(gameId, racingGame));
    }

    public Map<Long, List<GameResultWithCarDto>> findAll() {
        return gameResultDao.findAll();
    }
}
