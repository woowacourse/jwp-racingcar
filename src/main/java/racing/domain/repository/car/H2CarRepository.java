package racing.domain.repository.car;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racing.domain.Car;
import racing.domain.Cars;
import racing.persistence.h2.car.CarDao;
import racing.persistence.h2.car.CarEntity;

@Repository
public class H2CarRepository implements CarRepository {

    private final CarDao carDao;

    public H2CarRepository(CarDao carDao) {
        this.carDao = carDao;
    }

    public void saveCarsInGame(Cars cars, Long gameId) {
        int winnerStep = cars.getMaxStep();
        List<CarEntity> carEntities = cars.getCars().stream()
                .map(car -> CarEntity.of(gameId, car, isWinner(winnerStep, car)))
                .collect(Collectors.toList());

        carDao.saveAllCar(carEntities);
    }

    private boolean isWinner(int winnerStep, Car car) {
        return car.getStep() == winnerStep;
    }
}
