package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Player;
import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private final RacingCarGameDao racingCarGameDao;

    public CarRepository(RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    public Car save(Car car) {
        Long gameId = car.getGameId();

        Player player = racingCarGameDao.findPlayerByGameIdAndName(gameId, car.getName())
                .orElse(new Player(car.getName(), car.getPosition(), gameId));

        if (player.getPlayerId() == null) {
            racingCarGameDao.insertPlayer(player);
            return new Car(player.getName(), player.getGameId(), player.getPosition());
        }

        racingCarGameDao.updatePlayer(player);
        return new Car(player.getName(), player.getGameId(), player.getPosition());
    }

    public Cars saveAll(Cars cars) {
        List<Car> saved = new ArrayList<>();
        for (Car car : cars.getCars()) {
            saved.add(save(car));
        }

        return new Cars(saved);
    }

}
