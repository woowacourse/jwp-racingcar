package racing.domain.service;

import org.springframework.stereotype.Service;
import racing.CarEntity;
import racing.RacingGameDao;
import racing.domain.Cars;

import java.util.List;
import java.util.Random;

@Service
public class RacingGameService {

    private final RacingGameDao racingGameDao;

    public RacingGameService(RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public void move(Cars cars, int count) {
        while(count-- > 0) {
            cars.calculator(new Random());
        }
    }

    public Long createRacingGame(int count) {
        return racingGameDao.saveGame(count);
    }

    public void saveCarsState(Long gameId, Cars cars) {
        List<String> winners = cars.getWinners();

        cars.getCars().stream()
                .map(car -> CarEntity.of(gameId, car, winners.contains(car.getName())))
                .forEach(racingGameDao::saveCar);
    }

    public String getWinners(Cars cars) {
        List<String> winners = cars.getWinners();
        return String.join("", winners);
    }

}
