package racing.persistence.h2.car;

import java.util.List;

public interface CarDao {
    void saveAllCar(List<CarEntity> carEntities);

    List<CarEntity> findCarsInGame(List<Long> gameIds);

    List<CarEntity> findCarsInGame(Long gameId);
}
