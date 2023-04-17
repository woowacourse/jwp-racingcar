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
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
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

    public GamePlayResponseDto play(final GamePlayRequestDto gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final GameEntity game = new GameEntity(gameRequest.getCount());
        final int gameId = gameDao.saveAndGetId(game);

        final List<Car> cars = racingGame.getCars();
        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        final List<CarEntity> players = cars.stream()
                .map(car -> CarEntity.of(car, winners.contains(car.getName()), gameId))
                .collect(toList());
        carDao.saveAll(players);

        return toGameResponse(racingGame.findWinners(), cars);
    }

    private RacingGame playRacingGame(final GamePlayRequestDto gameRequest) {
        final RacingGame racingGame = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());
        racingGame.play();
        return racingGame;
    }

    private GamePlayResponseDto toGameResponse(final List<String> winners, final List<Car> cars) {
        final List<CarDto> carDtos = cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(toList());
        return new GamePlayResponseDto(winners, carDtos);
    }
}
