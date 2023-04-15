package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.GamePlayDto;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerResultRepository playerResultRepository;

    @Autowired
    public GameService(final GameRepository gameRepository, final PlayerResultRepository playerResultRepository) {
        this.gameRepository = gameRepository;
        this.playerResultRepository = playerResultRepository;
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

    private Cars makeCars(List<String> carNames) {
        List<Car> baseCars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(baseCars);
    }

    private Game createGame(final String winners, final int trialCount) {
        GameSaveDto gameSaveDto = new GameSaveDto(winners, trialCount);
        Game game = gameRepository.createGame(gameSaveDto);
        return game;
    }

    private List<PlayerResult> createPlayerResults(final Cars cars, final Game game) {
        final List<PlayerResult> playerResults = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            final PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(game.getId(), car);
            final PlayerResult playerResult = playerResultRepository.savePlayerResult(playerResultSaveDto);
            playerResults.add(playerResult);
        }
        return playerResults;
    }
}
