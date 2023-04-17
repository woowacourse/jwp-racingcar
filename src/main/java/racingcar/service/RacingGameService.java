package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@Service
public class RacingGameService {
    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(final NumberGenerator numberGenerator, final GameDao gameDao, final CarDao carDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public GameResponseDto play(final GameRequestDto gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final GameEntity game = new GameEntity(gameRequest.getCount());
        final int gameId = gameDao.saveAndGetId(game);

        final List<Car> cars = racingGame.findCurrentCarPositions();
        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        final List<CarEntity> players = cars.stream()
                .map(car -> CarEntity.of(car, winners.contains(car.getName()), gameId))
                .collect(toList());
        carDao.saveAll(players);

        return toGameResponse(racingGame.findWinners(), cars);
    }

    private RacingGame playRacingGame(final GameRequestDto gameRequest) {
        final RacingGame racingGame = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());
        racingGame.play();
        return racingGame;
    }

    private GameResponseDto toGameResponse(final List<String> winners, final List<Car> cars) {
        final List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(toList());
        return new GameResponseDto(winners, carDtos);
    }
}
