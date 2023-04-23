package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGameAddService {
    private final CarDao carDao;
    private final RacingGameDao racingGameDao;

    public RacingGameAddService(CarDao carDao, RacingGameDao racingGameDao) {
        this.carDao = carDao;
        this.racingGameDao = racingGameDao;
    }

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        Cars cars = toCars(racingGameRequest);
        RacingGame racingGame = new RacingGame(racingGameRequest.getCount(), cars);
        racingGame.run();

        Long racingGameId = racingGameDao.save(new racingcar.dao.entity.RacingGameEntity(racingGameRequest.getCount()));
        carDao.saveAll(toCarEntities(racingGame, racingGameId));
        return RacingGameResponse.of(racingGame, racingGameId);
    }

    private Cars toCars(RacingGameRequest racingGameRequest) {
        List<Car> cars = racingGameRequest.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    private List<CarEntity> toCarEntities(RacingGame game, Long racingGameId) {
        List<Car> winners = game.findWinners();
        return game.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winners.contains(car), racingGameId))
                .collect(Collectors.toList());
    }
}
