package racingcar.service;

import static java.util.Collections.unmodifiableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.TrialCount;
import racingcar.dto.RecordDto;
import racingcar.repository.GameDao;
import racingcar.repository.RecordDao;
import racingcar.response.PlayResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private static final int MIN_GAME_ID = 1;

    private final RacingGame racingGame;
    private final GameDao gameDao;
    private final RecordDao recordDao;

    @Autowired
    public GameService(final RacingGame racingGame, final GameDao gameDao, final RecordDao recordDao) {
        this.racingGame = racingGame;
        this.gameDao = gameDao;
        this.recordDao = recordDao;
    }

    @Transactional
    public PlayResponse playRacing(final String names, final int count) {
        Cars cars = racingGame.createCars(names);
        TrialCount trialCount = racingGame.createTrialCount(count);

        racingGame.playRacing(cars, trialCount);

        long savedGameId = gameDao.insert(trialCount.getValue());
        saveResults(savedGameId, cars);

        return PlayResponse.from(cars);
    }

    private void saveResults(final long gameId, final Cars cars) {
        List<String> winnerNames = cars.winnerNames();

        for (final Car car : cars.getCars()) {
            recordDao.insert(gameId, winnerNames.contains(car.getName()), car);
        }
    }

    public List<PlayResponse> showGameHistory() {
        List<PlayResponse> playResponses = new ArrayList<>();

        int count = gameDao.countAll();
        for (int gameId = MIN_GAME_ID; gameId <= count; gameId++) {
            List<RecordDto> recordsByGameId = recordDao.findAllByGameId(gameId);

            playResponses.add(PlayResponse.from(recordsByGameId));
        }

        return unmodifiableList(playResponses);
    }
}
