package racingcar.repositoryimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.dao.entity.SelectGameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.domain.RacingGameResult;
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
    public RacingGameResult save(final RacingGameResult racingGameResult) {
        final InsertGameEntity insertGameEntity = gamesDao.insert(InsertGameEntity.fromDomain(racingGameResult));
        final List<CarEntity> carEntities = fromCarsToEntity(racingGameResult.getTotalCars());
        final List<CarEntity> savedCarEntities = carDao.insertAll(carEntities, insertGameEntity.getGameId());
        final List<CarEntity> winnerCarEntities = findWinnerCarEntities(savedCarEntities,
                racingGameResult.getWinners());

        winnerDao.saveAll(winnerCarEntities, insertGameEntity.getGameId());
        return insertGameEntity.getRacingGameResult();
    }

    private List<CarEntity> fromCarsToEntity(final List<Car> cars) {
        return cars.stream()
                .map(CarEntity::fromDomain)
                .collect(Collectors.toList());
    }

    private List<CarEntity> findWinnerCarEntities(final List<CarEntity> savedCarEntities, final List<Car> winner) {
        final List<String> winnerNames = winner.stream().map(Car::getCarName).collect(Collectors.toList());

        return savedCarEntities.stream()
                .filter(carEntity -> winnerNames.contains(carEntity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RacingGameResult> findAll() {
        final List<SelectGameEntity> selectGameEntities = gamesDao.findAll();

        return mapToRacingGameResults(selectGameEntities);
    }

    private List<RacingGameResult> mapToRacingGameResults(final List<SelectGameEntity> selectGameEntities) {
        final List<RacingGameResult> racingGameResults = new ArrayList<>();
        for (SelectGameEntity selectGameEntity : selectGameEntities) {
            final int gameId = selectGameEntity.getGameId();
            final List<CarEntity> carEntities = carDao.findAllByGameId(gameId);
            final List<WinnerEntity> winnerEntities = winnerDao.findAllByGameId(gameId);

            racingGameResults.add(fromEntity(selectGameEntity, carEntities, winnerEntities));
        }
        return racingGameResults;
    }

    private RacingGameResult fromEntity(final SelectGameEntity selectGameEntity, final List<CarEntity> carEntities,
            final List<WinnerEntity> winnerEntities) {
        final List<Car> winners = carEntities.stream().filter(carEntity -> winnerEntities.stream()
                        .anyMatch(winnerEntity -> winnerEntity.getWinnerCarId() == carEntity.getId()))
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
        final List<Car> totalCars = carEntities.stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());

        return new RacingGameResult(totalCars, winners, new Count(selectGameEntity.getTryCount()));
    }
}
