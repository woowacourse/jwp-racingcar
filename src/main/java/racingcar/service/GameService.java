package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.repository.GameDao;

@Service
public class GameService {

    private final GameDao gameDao;
    private final RecordService recordService;

    public GameService(final GameDao gameDao, final RecordService recordService) {
        this.gameDao = gameDao;
        this.recordService = recordService;
    }

    public long save(final int count) {
        return gameDao.insert(count);
    }
}
