package racing.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.web.repository.CarEntity;
import racing.web.repository.RacingGameDao;

@Service
public class RacingGameService {

    private final RacingGameDao racingGameDao;

    public RacingGameService(RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public Long saveGameByCount(int count) {
        return racingGameDao.saveGame(count);
    }

    public void playGame(Long gameId, Cars cars) {
        int gameTrialCount = racingGameDao.findGameTrialById(gameId);
        cars.moveCarsByCount(gameTrialCount);
        List<String> winners = cars.getWinners();

        List<CarEntity> carEntities = cars.getCars().stream()
                .map(car -> CarEntity.of(gameId, car, winners.contains(car.getName())))
                .collect(Collectors.toList());
        racingGameDao.saveCar(carEntities);
    }

    public String filterWinnersToCarNames(Cars cars) {
        List<String> winners = cars.getWinners();
        return String.join(",", winners);
    }

    public List<Cars> getAllResults() {
        List<Long> gameIdOrderByRecent = racingGameDao.findAllGameIdOrderByRecent();
        List<CarEntity> carsInGame = racingGameDao.findCarsInGame(gameIdOrderByRecent);

        List<Cars> results = new ArrayList<>();
        for (Long gameId : gameIdOrderByRecent) {
            results.add(filterByGameId(carsInGame, gameId));
        }

        return results;
    }

    private Cars filterByGameId(List<CarEntity> carsInGame, Long gameId) {
        return new Cars(carsInGame.stream()
                .filter(carEntity -> carEntity.getGameId().equals(gameId))
                .map(this::toCarFrom)
                .collect(Collectors.toList()));
    }

    private Car toCarFrom(CarEntity carEntity) {
        CarName carName = new CarName(carEntity.getCarName());
        return new Car(carName, carEntity.getStep());
    }
}
