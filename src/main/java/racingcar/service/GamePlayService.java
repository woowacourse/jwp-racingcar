package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.dto.response.PlayerResultDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GamePlayService {

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    public GamePlayService(GameDao gameDao, PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

    public GameResponseDto playGame(GameRequestDto gameRequestDto) {
        List<String> carNames = List.of(gameRequestDto.getNames().split(",", -1));
        Cars cars = makeCars(carNames);
        RacingGame racingGame = new RacingGame(cars, gameRequestDto.getCount());
        racingGame.race(new RandomNumberGenerator());

        List<String> winners = cars.calculateWinners();

        Long gameId = createGame(String.join(",", winners), gameRequestDto.getCount());
        List<PlayerResultSaveDto> playerResults = createPlayerResults(cars, gameId);
        playerResultDao.savePlayerResult(playerResults);
        List<PlayerResultDto> playerResultsByGameId = playerResultDao.findPlayerResultsByGameId(gameId);
        return GameResponseDto.of(winners, playerResultsByGameId);
    }

    private Cars makeCars(List<String> carNames) {
        List<Car> baseCars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(baseCars);
    }

    private Long createGame(String winners, int trialCount) {
        GameSaveDto gameSaveDto = new GameSaveDto(winners, trialCount);
        return gameDao.createGame(gameSaveDto);
    }

    private List<PlayerResultSaveDto> createPlayerResults(Cars cars, Long gameId) {
        return cars.getLatestResult().stream()
                .map(car -> new PlayerResultSaveDto(gameId, car))
                .collect(Collectors.toList());
    }
}
