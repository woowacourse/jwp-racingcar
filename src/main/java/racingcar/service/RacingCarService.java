package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.PlayResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final CarDao carDao;

    public RacingCarService(final PlayResultDao playResultDao, final CarDao carDao) {
        this.playResultDao = playResultDao;
        this.carDao = carDao;
    }

    @Transactional
    public GameResultDto playGame(PlayRequestDto playRequest) {
        final RacingGame racingGame = createGame(playRequest.getNames());
        final int count = playRequest.getCount();

        race(count, racingGame);
        final List<Car> cars = racingGame.getCars();
        final String winners = String.join(", ", racingGame.getWinnerNames());

        saveGame(count, winners, cars);
        return new GameResultDto(cars, winners);
    }

    private RacingGame createGame(final String rawCarNames) {
        final List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void race(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private void saveGame(int count, String winners, List<Car> cars) {
        long savedId = playResultDao.insertAndReturnId(count, winners);
        carDao.insert(savedId, cars);
    }
}
