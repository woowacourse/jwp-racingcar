package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.Lap;
import racingcar.domain.RandomNumberGenerator;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameDao;
import racingcar.repository.PlayerResultDao;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameService {

    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    @Autowired
    public GameService(final GameDao gameDao, final PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

    @Transactional
    public GameResponseDto createGameResult(final GameRequestDto request) {
        final GameResponseDto response = playGame(request);
        saveGameResult(request.getCount(), response);
        return response;
    }

    private GameResponseDto playGame(final GameRequestDto request) {
        final Cars cars = new Cars(request.getNames());
        final Lap lap = new Lap(request.getCount());
        return GameRunner.race(cars, lap, new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER));
    }

    private void saveGameResult(final int tryCount, final GameResponseDto response) {
        final Game game = saveGame(response.getWinners(), tryCount);
        savePlayerResults(response, game.getId());
    }

    private Game saveGame(final String winners, final int trialCount) {
        final Game game = new Game(trialCount, winners);
        return gameDao.save(game);
    }

    private void savePlayerResults(final GameResponseDto gameResponse, final long gameId) {
        gameResponse.getRacingCars()
                .forEach(racingCar -> {
                    final PlayerResult playerResult =
                            new PlayerResult(racingCar.getName(), racingCar.getPosition(), gameId);
                    playerResultDao.save(playerResult);
                });
    }

    public List<GameResponseDto> getAll() {
        final Map<Game, List<PlayerResult>> allGames = new LinkedHashMap<>();
        for (final Game game : gameDao.findAll()) {
            allGames.put(game, playerResultDao.findByGameId(game.getId()));
        }
        return allGames.entrySet().stream()
                .map(entry -> GameResponseDto.createByEntity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toUnmodifiableList());
    }
}
