package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.NumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final CarDao carDao;
    private final RacingGameDao racingGameDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(CarDao carDao, RacingGameDao racingGameDao, NumberGenerator numberGenerator) {
        this.carDao = carDao;
        this.racingGameDao = racingGameDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponse play(RacingGameRequest racingGameRequest) {
        Cars cars = new Cars(racingGameRequest.getNamesList().stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        RacingGame game = new RacingGame(numberGenerator, racingGameRequest.getCount(), cars);
        while (!game.isEnd()) {
            game.playOneRound();
        }
        RacingGameResponse result = getResult(game);
        Long racingGameId = racingGameDao.save(result.getWinners(), racingGameRequest.getCount());
        carDao.saveAll(racingGameId, result.getRacingCars());
        return result;
    }

    private RacingGameResponse getResult(RacingGame racingGame) {
        Cars cars = racingGame.getCars();
        List<Car> winners = cars.findWinners();
        List<CarDto> racingCars = cars.getCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
        return new RacingGameResponse(getWinnerNames(winners), racingCars);
    }

    private List<String> getWinnerNames(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
