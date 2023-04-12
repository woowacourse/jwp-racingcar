package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.AdvanceJudgement;
import racingcar.domain.GameResult;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.Range;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@Service
public class RacingGameService {

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(GameDao gameDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public long run(List<String> carNames, int count) {
        RacingGame racingGame = initializeRoundManager(carNames);
        IntStream.range(0, count).forEach(ignored -> racingGame.runRound());

        long gameId = gameDao.save(count);

        Map<RacingCar, GameResult> results = racingGame.getResult();

        results.forEach((racingCar, isWin) ->
                carDao.save(RacingCarResultDto.of(racingCar, isWin.getValue(), gameId)));

        return gameId;
    }

    private RacingGame initializeRoundManager(List<String> carNames) {
        Range range = new Range(4, 9);
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        AdvanceJudgement advanceJudgement = new AdvanceJudgement(range, numberGenerator);
        RacingGame racingGame = new RacingGame(advanceJudgement);
        for (String carName : carNames) {
            racingGame.addRacingCar(new RacingCar(carName));
        }
        return racingGame;
    }

    public List<String> findWinnersById(long id) {
        return carDao.findWinnersById(id);
    }

    public List<RacingCarDto> findCarsById(long id) {
        return carDao.findCarsById(id);
    }
}
