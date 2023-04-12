package racingcar.service;

import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarService {

    private final Cars cars;
    private final int count;
    private final NumberGenerator numberGenerator;
    private final RacingCarGameDao racingCarGameDao;

    public RacingCarService(Cars cars, int count, NumberGenerator numberGenerator, RacingCarGameDao racingCarGameDao) {
        this.cars = cars;
        this.count = count;
        this.numberGenerator = numberGenerator;
        this.racingCarGameDao = racingCarGameDao;
    }

    public void move() {
        for (int i = 0; i < count; i++) {
            cars.moveAll(numberGenerator);
        }

    }

    public String getWinners() {
        String winners = cars.pickWinners().getAll().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        Long gameId = racingCarGameDao.insertGameWithKeyHolder(new Game(count, winners));
        List<Car> cars1 = getCars();
        for (Car car : cars1) {
            racingCarGameDao.insertPlayers(new Player(car.getName(), car.getPosition(), gameId));
        }

        return winners;
    }

    public List<Car> getCars() {
        return cars.getAll();
    }
}
