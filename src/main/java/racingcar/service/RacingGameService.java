package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
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
        RacingGame racingGame = initializeGame(carNames);

        for (int i = 0; i < count; i++) {
            racingGame.runRound();
        }
        long gameId = gameDao.save(count);
        List<RacingCarResultDto> results = racingGame.calculateResult()
                .entrySet()
                .stream()
                .map((e) -> RacingCarResultDto.of(e.getKey(), e.getValue().getValue(), gameId))
                .collect(Collectors.toUnmodifiableList());
        carDao.saveAll(results);
        return gameId;
    }

    private RacingGame initializeGame(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream().map(RacingCar::new).collect(Collectors.toUnmodifiableList());
        return new RacingGame(racingCars, new RandomNumberGenerator());
    }

    public List<String> findWinnersById(long id) {
        return carDao.findWinnersById(id);
    }

    public List<RacingCarDto> findCarsById(long id) {
        return carDao.findCarsById(id);
    }
}
