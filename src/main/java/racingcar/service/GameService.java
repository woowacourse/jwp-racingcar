package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.PlayRequest;
import racingcar.model.Cars;
import racingcar.repository.GameDao;

@Service
public class GameService {

    private final GameDao gameDao;

    @Autowired
    public GameService(final GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public long saveGame(final PlayRequest playRequest) {
        return gameDao.insert(playRequest.getCount());
    }

    public void play(final PlayRequest playRequest, final Cars cars) {
        for (int i = 0; i < playRequest.getCount(); i++) {
            cars.moveAll();
        }
    }
}
