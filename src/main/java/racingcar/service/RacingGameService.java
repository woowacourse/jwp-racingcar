package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.RacingGameRepository;
import racingcar.domain.*;
import racingcar.dto.*;
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

    public OneGameHistoryDto run(final List<String> inputNames, final int inputCount) {
        final List<Name> names = generateNames(inputNames);
        final RacingGame racingGame = new RacingGame(new RacingCars(generateRacingCars(names)), new TryCount(inputCount));
        final RacingCars racingCars = racingGame.moveCars();

        saveRacingCars(inputCount, racingCars);

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

    private void saveRacingCars(final int tryCount, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<Player> players = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerEntity(winnerNames, racingCar))
                .collect(toList());
        racingGameRepository.save(tryCount, players);
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
                .map(RacingGameFindDto::getPlayerFindDtos)
                .map(playerFindDtos -> new OneGameHistoryDto(generateWinners(playerFindDtos), generateRacingCarDtos(playerFindDtos)))
                .collect(toList());
    }

    private String generateWinners(List<PlayerFindDto> playerFindDtos) {
        return playerFindDtos.stream()
                .filter(PlayerFindDto::getIsWinner)
                .map(PlayerFindDto::getName)
                .collect(Collectors.joining(","));
    }

    private List<RacingCarDto> generateRacingCarDtos(List<PlayerFindDto> playerFindDtos) {
        return playerFindDtos.stream()
                .map(playerFindDto -> new RacingCarDto(playerFindDto.getName(), playerFindDto.getPosition()))
                .collect(Collectors.toList());
    }
}
