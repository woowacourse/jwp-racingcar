package racingcar.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.CarRecordDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.car.Car;
import racingcar.domain.race.RacingGame;
import racingcar.domain.race.WinnerJudgeImpl;
import racingcar.dto.ResultDto;

@Service
public class RacingGameService {

    private final RacingHistoryDao racingHistoryDao;
    private final CarRecordDao carRecordDao;

    public RacingGameService(RacingHistoryDao racingHistoryDao, CarRecordDao carRecordDao) {
        this.racingHistoryDao = racingHistoryDao;
        this.carRecordDao = carRecordDao;
    }

    public ResultDto start(int trialCount, List<String> names) {
        RacingGame game = new RacingGame(names, new WinnerJudgeImpl());
        Long historyId = racingHistoryDao.insert(trialCount, LocalDateTime.now());
        game.progress(trialCount);
        insertCars(game, historyId);
        return ResultDto.from(game);
    }

    private void insertCars(RacingGame game, long historyId) {
        for (Car car : game.getRacingCars()) {
            carRecordDao.insert(historyId, car, game.isWinner(car));
        }
    }

}
