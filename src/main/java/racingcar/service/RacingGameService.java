package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
public class RacingGameService {
    private final CarDao carDao;
    private final RacingGameDao racingGameDao;

    public RacingGameService(CarDao carDao, RacingGameDao racingGameDao) {
        this.carDao = carDao;
        this.racingGameDao = racingGameDao;
    }

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        Cars cars = toCars(racingGameRequest);
        RacingGame racingGame = new RacingGame(racingGameRequest.getCount(), cars);
        while (!racingGame.isEnd()) {
            racingGame.playOneRound();
        }
        Long racingGameId = racingGameDao.save(new RacingGameEntity(racingGameRequest.getCount()));
        carDao.saveAll(toCarEntities(racingGame, racingGameId));
        return RacingGameResponse.of(racingGame);
    }

    private Cars toCars(RacingGameRequest racingGameRequest) {
        return new Cars(racingGameRequest.getNamesList().stream()
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    private List<CarEntity> toCarEntities(RacingGame game, Long racingGameId) {
        Cars cars = game.getCars();
        List<Car> winners = cars.findWinners();
        return cars.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winners.contains(car), racingGameId))
                .collect(Collectors.toList());
    }
}
