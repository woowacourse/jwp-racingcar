package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Service
@Transactional
public class RacingGameAddService {
    private final CarDao carDao;
    private final RacingGameDao racingGameDao;

    public RacingGameAddService(CarDao carDao, RacingGameDao racingGameDao) {
        this.carDao = carDao;
        this.racingGameDao = racingGameDao;
    }

    public RacingGameResponse addGame(RacingGameRequest racingGameRequest) {
        List<Car> cars = racingGameRequest.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());
        RacingGame racingGame = new RacingGame(racingGameRequest.getCount(), new Cars(cars));
        racingGame.run();
        return RacingGameResponse.from(racingGame);
    }

    private List<CarEntity> toCarEntities(RacingGame game, Long racingGameId) {
        List<Car> winners = game.findWinners();
        return game.getCars().stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winners.contains(car), racingGameId))
                .collect(Collectors.toList());
    }
}
