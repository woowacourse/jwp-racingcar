package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.RacingCarResultDto;

@Service
public class RacingGameService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(GameDao gameDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public List<RacingCarResultDto> run(List<String> carNames, int count) {
        List<RacingCar> racingCars = carNames.stream().map(RacingCar::new).collect(Collectors.toUnmodifiableList());
        RacingGame racingGame = initializeGame(racingCars);

        for (int i = 0; i < count; i++) {
            racingGame.runRound();
        }
        long gameId = gameDao.save(count);
        List<RacingCarResultDto> results = calculateResults(racingGame, gameId);
        carDao.saveAll(results);

        return results;
    }

    public List<List<RacingCarResultDto>> history() {
        List<List<RacingCarResultDto>> history = new ArrayList<>();
        List<Long> ids = gameDao.findAllId();
        for (Long id : ids) {
            List<RacingCar> racingCars = carDao.findCarsById(id)
                    .stream()
                    .map(it -> new RacingCar(it.getName(), it.getPosition()))
                    .collect(Collectors.toList());
            RacingGame racingGame = initializeGame(racingCars);
            List<RacingCarResultDto> results = calculateResults(racingGame, id);
            history.add(results);
        }
        return history;
    }

    private RacingGame initializeGame(List<RacingCar> cars) {
        return new RacingGame(cars, new RandomNumberGenerator());
    }

    private static List<RacingCarResultDto> calculateResults(RacingGame racingGame, long gameId) {
        List<RacingCarResultDto> results = racingGame.calculateResult()
                .entrySet()
                .stream()
                .map((e) -> RacingCarResultDto.of(e.getKey(), e.getValue().getValue(), gameId))
                .collect(Collectors.toUnmodifiableList());
        return results;
    }
}
