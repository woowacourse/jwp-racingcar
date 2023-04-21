package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.ApplicationType;
import racingcar.dao.JdbcRacingGameRepository;
import racingcar.dao.RacingGameRepository;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.dto.GameHistoryDto;
import racingcar.dto.RacingCarDto;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.entity.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class RacingGameService {

    private final RacingGameRepository racingGameRepository;

    public RacingGameService(final RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public static RacingGameService generateDefaultRacingGameService() {
        return new RacingGameService(JdbcRacingGameRepository.generateDefaultJdbcRacingGameRepository());
    }

    public GameHistoryDto playGame(final List<String> inputNames, final int inputCount, final ApplicationType applicationType) {
        final List<Name> names = generateNames(inputNames);
        final racingcar.domain.RacingGame racingGame = new racingcar.domain.RacingGame(new RacingCars(generateRacingCars(names)), new TryCount(inputCount));
        final RacingCars racingCars = racingGame.moveCars();

        saveRacingCars(inputCount, applicationType, racingCars);

        return generateOneGameHistoryDto(racingCars);
    }

    private List<Name> generateNames(final List<String> inputNames) {
        return inputNames.stream()
                .map(Name::new)
                .collect(toList());
    }

    private List<RacingCar> generateRacingCars(final List<Name> names) {
        return names.stream()
                .map(RacingCar::createRandomMoveRacingCar)
                .collect(toList());
    }

    private void saveRacingCars(final int tryCount, final ApplicationType applicationType, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<Player> players = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerEntity(winnerNames, racingCar))
                .collect(toList());
        final Game game = new Game(tryCount, applicationType);
        racingGameRepository.save(new RacingGame(game, players));
    }

    private Player createPlayerEntity(final List<String> winnerNames, final RacingCar racingCar) {
        return new Player(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }

    private GameHistoryDto generateOneGameHistoryDto(final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final String winnerName = String.join(", ", winnerNames);

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());
        return new GameHistoryDto(winnerName, racingCarsDto);
    }

    public List<GameHistoryDto> findRacingGameHistory() {
        final List<RacingGame> racingGames = racingGameRepository.findAll();
        return generateOneGameHistoryDtos(racingGames);
    }

    private List<GameHistoryDto> generateOneGameHistoryDtos(final List<RacingGame> racingGames) {
        return racingGames.stream()
                .map(RacingGame::getPlayer)
                .map(players -> new GameHistoryDto(generateWinners(players), generateRacingCarDtos(players)))
                .collect(toList());
    }

    private String generateWinners(final List<Player> players) {
        return players.stream()
                .filter(Player::getIsWinner)
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    private List<RacingCarDto> generateRacingCarDtos(final List<Player> players) {
        return players.stream()
                .map(player -> new RacingCarDto(player.getName(), player.getPosition()))
                .collect(Collectors.toList());
    }
}
