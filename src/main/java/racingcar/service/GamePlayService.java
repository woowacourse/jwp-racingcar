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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamePlayService {

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    public GamePlayService(GameDao gameDao, PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

    @Transactional
    public GameResponseDto playGame(GameRequestDto gameRequestDto) {
        List<String> carNames = List.of(gameRequestDto.getNames().split(",", -1));
        Cars cars = makeCars(carNames);
        RacingGame racingGame = new RacingGame(cars, gameRequestDto.getCount());
        racingGame.race(new RandomNumberGenerator());

        List<String> winners = cars.calculateWinners();

        Long gameId = createGame(String.join(",", winners), gameRequestDto.getCount());
        List<PlayerResultDto> playerResultDtos = createPlayerResults(cars, gameId);
        return GameResponseDto.of(winners, playerResultDtos);
    }

    private Cars makeCars(List<String> carNames) {
        List<Car> baseCars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(baseCars);
    }

    private Long createGame(String winners, int trialCount) {
        GameSaveDto gameSaveDto = new GameSaveDto(winners, trialCount);
        Long gameId = gameDao.createGame(gameSaveDto);
        return gameId;
    }

    private List<PlayerResultDto> createPlayerResults(Cars cars, Long gameId) {
        List<PlayerResultDto> playerResults = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(gameId, car);
            PlayerResultDto playerResultDto = playerResultDao.savePlayerResult(playerResultSaveDto);
            playerResults.add(playerResultDto);
        }
        return playerResults;
    }
}
