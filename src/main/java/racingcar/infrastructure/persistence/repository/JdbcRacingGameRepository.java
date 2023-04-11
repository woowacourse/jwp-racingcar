package racingcar.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
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
        racingGame.getCars().getCars()
                .stream()
                .map(it -> new CarEntity(it, id))
                .forEach(carDao::save);
        racingGame.winners().getWinners()
                .stream()
                .map(it -> new WinnerEntity(it, id))
                .forEach(winnerDao::save);
        return id;
    }

    @Override
    public Optional<RacingGame> findById(final Long id) {
        final Optional<RacingGameEntity> racingGameEntity = racingGameDao.findById(id);
        if (racingGameEntity.isEmpty()) {
            return Optional.empty();
        }
        final RacingGameEntity get = racingGameEntity.get();
        final List<CarEntity> carEntityByGameId = carDao.findByGameId(id);
        final List<Car> carsByGameId = carEntityByGameId.stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
        final Cars cars = new Cars(carsByGameId);
        return Optional.of(new RacingGame(cars, new GameTime(String.valueOf(get.getTrialCount()))));
    }
}
