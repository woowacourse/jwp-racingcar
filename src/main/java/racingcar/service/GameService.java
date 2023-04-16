package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import racingcar.dto.response.GameResultDto;
import racingcar.dto.response.GameWinnerDto;
import racingcar.dto.response.PlayerResultDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    @Autowired
    public GameService(final GameDao gameDao, final PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

//    @Transactional
//    public GameResponseDto playGame(GamePlayDto gamePlayDto) {
//        Cars cars = makeCars(gamePlayDto.getNames());
//        RacingGame racingGame = new RacingGame(cars, gamePlayDto.getCount());
//        racingGame.race(new RandomNumberGenerator());
//
//        List<String> winners = cars.calculateWinners();
//
//        final Long gameId = createGame(String.join(",", winners), gamePlayDto.getCount());
//        final List<PlayerResultDto> playerResults = createPlayerResults(cars, gameId);
//        return GameResponseDto.of(winners, playerResults);
//    }

    @Transactional
    public GameResponseDto playGame(GameRequestDto gameRequestDto) {
        List<String> carNames = List.of(gameRequestDto.getNames().split(",", -1));
        Cars cars = makeCars(carNames);
        RacingGame racingGame = new RacingGame(cars, gameRequestDto.getCount());
        racingGame.race(new RandomNumberGenerator());

        List<String> winners = cars.calculateWinners();

        final Long gameId = createGame(String.join(",", winners), gameRequestDto.getCount());
        final List<PlayerResultDto> playerResults = createPlayerResults(cars, gameId);
        return GameResponseDto.of(winners, playerResults);
    }

    public List<GameResultDto> findAllGames() {
        List<GameResultDto> gameResultDtos = new ArrayList<>();
        List<GameWinnerDto> allGames = gameDao.findAllGames();

        for (GameWinnerDto game : allGames) {
            List<PlayerResultDto> playerResultsDto = playerResultDao.findPlayerResultsByGameId(game.getGameId());
            gameResultDtos.add(new GameResultDto(game.getWinners(), playerResultsDto));
        }

        return gameResultDtos;
    }

    private Cars makeCars(List<String> carNames) {
        List<Car> baseCars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(baseCars);
    }

    private Long createGame(final String winners, final int trialCount) {
        GameSaveDto gameSaveDto = new GameSaveDto(winners, trialCount);
        Long gameId = gameDao.createGame(gameSaveDto);
        return gameId;
    }

    private List<PlayerResultDto> createPlayerResults(final Cars cars, final Long gameId) {
        final List<PlayerResultDto> playerResults = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            final PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(gameId, car);
            final PlayerResultDto playerResultDto = playerResultDao.savePlayerResult(playerResultSaveDto);
            playerResults.add(playerResultDto);
        }
        return playerResults;
    }
}
