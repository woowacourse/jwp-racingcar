package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.car.CarDao;
import racingcar.dao.gameresult.GameResultDao;
import racingcar.domain.RacingGame;
import racingcar.dto.db.GameResultWithCarDto;
import racingcar.utils.DtoMapper;

import java.util.List;
import java.util.Map;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDaoWebImpl;
    private final CarDao carDaoWebImpl;

    public RacingGameRepository(final GameResultDao gameResultDaoWebImpl, final CarDao carDaoWebImpl) {
        this.gameResultDaoWebImpl = gameResultDaoWebImpl;
        this.carDaoWebImpl = carDaoWebImpl;
    }

    public void save(final RacingGame racingGame) {
        final Long gameId = gameResultDaoWebImpl.save(DtoMapper.toRacingGameDto(racingGame));
        carDaoWebImpl.saveAll(DtoMapper.toCarsDto(gameId, racingGame));
    }

    public Map<Long, List<GameResultWithCarDto>> findAll() {
        return gameResultDaoWebImpl.findAll();
    }
}
