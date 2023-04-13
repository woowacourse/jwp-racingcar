package racingcar;

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
        Long historyId = racingHistoryDao.save(trialCount, LocalDateTime.now());
        game.progress(trialCount);
        saveCars(game, historyId);
        return new ResultDto(game.getRacingCars(), game.getWinners());
    }

    private void saveCars(RacingGame game, long historyId) {
        for (Car car : game.getRacingCars()) {
            carRecordDao.save(historyId, car, game.isWinner(car));
        }
    }

}
