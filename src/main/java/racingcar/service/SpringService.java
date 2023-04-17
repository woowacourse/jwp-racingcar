package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.TrialCount;
import racingcar.dto.ResponseDto;

@Service
public class SpringService {

    private GameDao gameDao;
    private GameLogDao gameLogDao;
    private WinnersDao winnersDao;

    public SpringService() {
    }

    @Autowired
    public SpringService(GameDao gameDao, GameLogDao gameLogDao, WinnersDao winnersDao) {
        this.gameDao = gameDao;
        this.gameLogDao = gameLogDao;
        this.winnersDao = winnersDao;
    }

    public ResponseDto play(Cars cars, int playCount) {
        TrialCount trialCount = TrialCount.of(playCount);
        int gameNumber = gameDao.saveGame(trialCount);
        playMultipleTimes(cars, trialCount);
        cars.saveGameLog(gameLogDao, gameNumber);
        cars.saveGameWinner(winnersDao, gameNumber);
        return new ResponseDto(getWinners(cars), getCars(cars));
    }

    private void playMultipleTimes(Cars cars, TrialCount trialCount) {
        for (int i = 0; i < trialCount.getTrialCount(); i++) {
            playOnce(cars);
        }
    }

    public void playOnce(Cars cars) {
        cars.moveCars(new RandomMoveChance());
    }

    public List<Car> getWinners(Cars cars) {
        return cars.getWinners();
    }

    public List<Car> getCars(Cars cars) {
        return cars.getCars();
    }
}
