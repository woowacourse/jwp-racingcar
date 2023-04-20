package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.repository.GameDao;

@Service
public class GameService {

    private final GameDao gameDao;

    public GameService(final GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public long save(final int count) {
        return gameDao.insert(count);
    }
}
