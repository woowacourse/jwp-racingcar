package racingcar.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.*;
import racingcar.infrastructure.persistence.dao.CarDao;
import racingcar.infrastructure.persistence.dao.RacingGameDao;
import racingcar.infrastructure.persistence.dao.WinnerDao;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public JdbcRacingGameRepository(final RacingGameDao racingGameDao,
                                    final CarDao carDao,
                                    final WinnerDao winnerDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    @Override
    public Long save(final RacingGame racingGame) {
        final RacingGameEntity racingGameEntity = new RacingGameEntity(racingGame);
        final Long id = racingGameDao.save(racingGameEntity);
        saveCars(racingGame, id);
        saveWinners(racingGame, id);
        return id;
    }

    private void saveCars(final RacingGame racingGame, final Long id) {
        racingGame.getCars()
                .stream()
                .map(it -> new CarEntity(it, id))
                .forEach(carDao::save);
    }

    private void saveWinners(final RacingGame racingGame, final Long id) {
        racingGame.winners().getWinners()
                .stream()
                .map(it -> new WinnerEntity(it, id))
                .forEach(winnerDao::save);
    }

    @Override
    public Optional<RacingGame> findById(final Long id) {
        final Optional<RacingGameEntity> racingGameEntity = racingGameDao.findById(id);
        return racingGameEntity.map(gameEntity -> toRacingGame(id, gameEntity));
    }

    private RacingGame toRacingGame(final Long id, final RacingGameEntity racingGameEntity) {
        final List<Car> cars = carDao.findByGameId(id)
                .stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
        return new RacingGame(
                new Cars(cars),
                new GameTime(racingGameEntity.getTrialCount()));
    }
}
