package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.GameRecordDao;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.*;
import racingcar.controller.dto.GameResultDto;

import java.util.List;

@Service
public class RacingCarService {

    private final ResultDao resultDao;
    private final RacingCarDao racingCarDao;
    private final GameRecordDao gameRecordDao;

    public RacingCarService(ResultDao resultDao, RacingCarDao racingCarDao, GameRecordDao gameRecordDao) {
        this.resultDao = resultDao;
        this.racingCarDao = racingCarDao;
        this.gameRecordDao = gameRecordDao;
    }

    public void insertGame(int trialCount, Cars cars) {
        long resultId = resultDao.insert(trialCount, cars.getWinnerCars());

        for (Car car : cars.getCars()) {
            racingCarDao.insert(car, resultId);
        }
    }

    public List<GameResultDto> findGameRecord() {
        return gameRecordDao.findGameRecord();
    }

    public ResultDao getResultDao() {
        return resultDao;
    }

    public RacingCarDao getRacingCarDao() {
        return racingCarDao;
    }
}
