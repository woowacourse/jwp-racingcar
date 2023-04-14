package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(final NumberGenerator numberGenerator, final GameDao gameDao, final CarDao carDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public GameResponse play(final GameRequest gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final String winners = String.join(DELIMITER, racingGame.findWinners());
        final int gameId = gameDao.save(gameRequest.getCount(), winners);

        final List<Car> cars = racingGame.findCurrentCarPositions();
        carDao.saveAll(gameId, cars);

        return toGameResponse(winners, cars);
    }

    private RacingGame playRacingGame(final GameRequest gameRequest) {
        final List<String> names = Arrays.stream(gameRequest.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final RacingGame racingGame = new RacingGame(numberGenerator, names, gameRequest.getCount());
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        return racingGame;
    }

    private GameResponse toGameResponse(final String winners, final List<Car> cars) {
        final List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        return new GameResponse(winners, carDtos);
    }
}
