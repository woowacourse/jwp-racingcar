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
import racingcar.dto.request.GamePlayDto;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.dto.response.GameResultDto;
import racingcar.dto.response.GameWinnerDto;
import racingcar.dto.response.PlayerResultDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;

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

    @Transactional
    public GameResponseDto playGame(GamePlayDto gamePlayDto) {
        Cars cars = makeCars(gamePlayDto.getNames());
        RacingGame racingGame = new RacingGame(cars, gamePlayDto.getCount());
        racingGame.race(new RandomNumberGenerator());

        List<String> winners = cars.calculateWinners();

        final Game game = createGame(String.join(",", winners), gamePlayDto.getCount());
        final List<PlayerResult> playerResults = createPlayerResults(cars, game);
        return GameResponseDto.of(game, playerResults);
    }

    public List<GameResultDto> findAllGames() {
        List<GameResultDto> gameResultDtos = new ArrayList<>();
        List<GameWinnerDto> allGames = gameDao.findAllGames();

        for (GameWinnerDto game : allGames) {
            List<PlayerResultDto> playerResultsDto = playerResultDao.findPlayerResultsByGameId(game.getGameId());
            System.out.println(playerResultsDto.size());
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

    private Game createGame(final String winners, final int trialCount) {
        GameSaveDto gameSaveDto = new GameSaveDto(winners, trialCount);
        Game game = gameDao.createGame(gameSaveDto);
        return game;
    }

    private List<PlayerResult> createPlayerResults(final Cars cars, final Game game) {
        final List<PlayerResult> playerResults = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            final PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(game.getId(), car);
            final PlayerResult playerResult = playerResultDao.savePlayerResult(playerResultSaveDto);
            playerResults.add(playerResult);
        }
        return playerResults;
    }
}
