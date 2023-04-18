package racingcar.repositoryImpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

@Repository
public class RacingGameRepositoryImpl implements RacingGameRepository {

    private final GamesDao gamesDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public RacingGameRepositoryImpl(final GamesDao gamesDao, final CarDao carDao, final WinnerDao winnerDao) {
        this.gamesDao = gamesDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    @Override
    public RacingGame insert(final RacingGame racingGame) {
        final GameEntity gameEntity = gamesDao.save(RacingGameMapper.toGameEntity(racingGame));
        final List<CarEntity> carEntities = RacingGameMapper.toCarEntities(racingGame.getCars(),
                gameEntity.getGameId());
        final List<CarEntity> savedCarEntities = carDao.insertAll(carEntities);

        final RacingGame savedRacingGame = RacingGameMapper.toDomain(gameEntity, savedCarEntities);
        final List<WinnerEntity> winnerEntities = RacingGameMapper.toWinnerEntity(savedRacingGame);
        winnerDao.insertAll(winnerEntities);
        return savedRacingGame;
    }
}
