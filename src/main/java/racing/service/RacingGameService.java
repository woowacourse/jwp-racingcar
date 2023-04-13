package racing.service;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racing.repositroy.CarEntity;
import racing.repositroy.RacingGameDao;
import racing.domain.Cars;

import java.util.List;

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
        cars.moveCars(gameTrialCount);
        List<String> winners = cars.getWinners();

        List<CarEntity> carEntities = cars.getCars().stream()
                .map(car -> CarEntity.of(gameId, car, winners.contains(car.getName())))
                .collect(Collectors.toList());
        racingGameDao.saveCar(carEntities);
    }

    public String getWinners(Cars cars) {
        List<String> winners = cars.getWinners();
        return String.join("", winners);
    }

}
