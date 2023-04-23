package racingcar.repositoryImpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.GamesDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

@Repository
public class RacingGameRepositoryImpl implements RacingGameRepository {

    private final GamesDao gamesDao;
    private final CarDao carDao;

    public RacingGameRepositoryImpl(final GamesDao gamesDao, final CarDao carDao) {
        this.gamesDao = gamesDao;
        this.carDao = carDao;
    }

    @Override
    public RacingGame save(final RacingGame racingGame) {
        final GameEntity gameEntity = gamesDao.insert(RacingGameMapper.toGameEntity(racingGame));
        final List<CarEntity> carEntities = RacingGameMapper.toCarEntities(racingGame.getCars(),
                gameEntity.getGameId());
        final List<CarEntity> savedCarEntities = carDao.insertAll(carEntities);

        return RacingGameMapper.toDomain(gameEntity, savedCarEntities);
    }

    @Override
    public List<RacingGame> findAll() {
        final List<GameEntity> gameEntities = gamesDao.findAll();
        final List<CarEntity> carEntities = carDao.findAll();
        return RacingGameMapper.toDomain(gameEntities, carEntities);
    }
}
