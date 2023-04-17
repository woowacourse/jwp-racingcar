package racingcar.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.NumberGenerator;
import racingcar.domain.game.RacingGame;
import racingcar.dto.RacingGameDto;

@Service
public class RacingGameService {

    private final RacingGameHistoryDao racingGameHistoryDao;
    private final RacingCarRecordDao racingCarRecordDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(RacingGameHistoryDao racingGameHistoryDao, RacingCarRecordDao racingCarRecordDao,
                             NumberGenerator numberGenerator) {
        this.racingGameHistoryDao = racingGameHistoryDao;
        this.racingCarRecordDao = racingCarRecordDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameDto play(int trialCount, List<String> names) {
        RacingGame game = RacingGame.from(names);
        game.play(trialCount, numberGenerator);

        Long historyId = racingGameHistoryDao.insert(trialCount, LocalDateTime.now());
        insertCars(game, historyId);

        return RacingGameDto.from(game);
    }

    private void insertCars(RacingGame game, long historyId) {
        for (RacingCar racingCar : game.getRacingCars()) {
            racingCarRecordDao.insert(historyId, racingCar, game.isWinner(racingCar));
        }
    }

}
