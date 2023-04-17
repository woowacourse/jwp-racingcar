package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.entity.Game;
import racingcar.entity.Player;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public RacingGameService(final NumberGenerator numberGenerator, final GameDao gameDao, final PlayerDao carDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.playerDao = carDao;
    }

    public GameResponse play(final GameRequest gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final Game game = new Game(gameRequest.getCount());
        final int gameId = gameDao.saveAndGetId(game);

        final List<Car> cars = racingGame.findCurrentCarPositions();
        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        final List<Player> players = cars.stream()
                .map(car -> Player.of(car, winners.contains(car.getName()), gameId))
                .collect(toList());
        playerDao.saveAll(players);

        return toGameResponse(String.join(DELIMITER, racingGame.findWinners()), cars);
    }

    private RacingGame playRacingGame(final GameRequest gameRequest) {
        final List<String> names = Arrays.stream(gameRequest.getNames().split(DELIMITER))
                .collect(toList());
        final RacingGame racingGame = new RacingGame(numberGenerator, names, gameRequest.getCount());
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        return racingGame;
    }

    private GameResponse toGameResponse(final String winners, final List<Car> cars) {
        final List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(toList());
        return new GameResponse(winners, carDtos);
    }
}
