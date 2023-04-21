package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.ApplicationType;
import racingcar.dao.JdbcRacingGameRepository;
import racingcar.dao.RacingGameRepository;
import racingcar.domain.*;
import racingcar.dto.OneGameHistoryDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameFindDto;
import racingcar.entity.Game;
import racingcar.entity.Player;

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

    public OneGameHistoryDto playGame(final List<String> inputNames, final int inputCount, final ApplicationType applicationType) {
        final List<Name> names = generateNames(inputNames);
        final RacingGame racingGame = new RacingGame(new RacingCars(generateRacingCars(names)), new TryCount(inputCount));
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
        racingGameRepository.save(game, players);
    }

    private Player createPlayerEntity(final List<String> winnerNames, final RacingCar racingCar) {
        return new Player(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }

    private OneGameHistoryDto generateOneGameHistoryDto(RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final String winnerName = String.join(", ", winnerNames);

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());
        return new OneGameHistoryDto(winnerName, racingCarsDto);
    }

    public List<OneGameHistoryDto> findRacingGameHistory() {
        final List<RacingGameFindDto> racingGameFindDtos = racingGameRepository.findAll();
        return generateOneGameHistoryDtos(racingGameFindDtos);
    }

    private List<OneGameHistoryDto> generateOneGameHistoryDtos(List<RacingGameFindDto> racingGameFindDtos) {
        return racingGameFindDtos.stream()
                .map(RacingGameFindDto::getPlayer)
                .map(players -> new OneGameHistoryDto(generateWinners(players), generateRacingCarDtos(players)))
                .collect(toList());
    }

    private String generateWinners(List<Player> players) {
        return players.stream()
                .filter(Player::getIsWinner)
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    private List<RacingCarDto> generateRacingCarDtos(List<Player> players) {
        return players.stream()
                .map(player -> new RacingCarDto(player.getName(), player.getPosition()))
                .collect(Collectors.toList());
    }
}
