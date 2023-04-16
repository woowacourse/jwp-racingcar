package racingcar.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.CarRecordDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.car.Car;
import racingcar.domain.race.NumberGenerator;
import racingcar.domain.race.RacingGame;
import racingcar.domain.race.WinnerJudgeImpl;
import racingcar.dto.RacingGameDto;

@Service
public class RacingGameService {

    private final RacingHistoryDao racingHistoryDao;
    private final CarRecordDao carRecordDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(RacingHistoryDao racingHistoryDao, CarRecordDao carRecordDao, NumberGenerator numberGenerator) {
        this.racingHistoryDao = racingHistoryDao;
        this.carRecordDao = carRecordDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameDto start(int trialCount, List<String> names) {
        RacingGame game = new RacingGame(names, new WinnerJudgeImpl());
        game.move(trialCount, numberGenerator);

        Long historyId = racingHistoryDao.insert(trialCount, LocalDateTime.now());
        insertCars(game, historyId);

        return RacingGameDto.from(game);
    }

    private void insertCars(RacingGame game, long historyId) {
        for (Car car : game.getRacingCars()) {
            carRecordDao.insert(historyId, car, game.isWinner(car));
        }
    }

}
