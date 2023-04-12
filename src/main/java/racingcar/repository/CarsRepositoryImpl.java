package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.service.CarsRepository;

@Repository
public class CarsRepositoryImpl implements CarsRepository {

    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public CarsRepositoryImpl(final CarDao carDao, final WinnerDao winnerDao) {
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    @Override
    public List<Car> findCars(final int gameId) {
        final List<CarEntity> carEntities = carDao.findByGameId(gameId);

        return carEntities.stream()
                .map(CarEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(final Cars cars, final int gameId) {
        final List<CarEntity> carEntities = cars.getCars()
                .stream()
                .map(car -> carDao.save(car, gameId))
                .collect(Collectors.toList());

        final List<String> winner = cars.findWinner();

        for (final String winnersName : winner) {
            winnerDao.save(gameId, findWinnerCarId(carEntities, winnersName));
        }
    }

    private int findWinnerCarId(final List<CarEntity> carEntities, final String winnersName) {
        return carEntities.stream()
                .filter(carEntity -> carEntity.getName().equals(winnersName))
                .findAny()
                .map(CarEntity::getId)
                .orElseThrow();
    }

    @Override
    public List<Car> findWinner(final int gameId) {
        final List<Integer> winnerCarIds = winnerDao.findByGameId(gameId);

        return winnerCarIds
                .stream()
                .map(carDao::findById)
                .map(CarEntity::toDomain)
                .collect(Collectors.toList());
    }
}
