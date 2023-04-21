package racing.domain.repository.car;

import racing.domain.Cars;

public interface CarRepository {

    void saveCarsInGame(Cars cars, Long gameId);
}
