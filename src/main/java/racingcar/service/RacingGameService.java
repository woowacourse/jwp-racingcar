package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.JdbcRacingGameRepository;
import racingcar.dao.RacingGameRepository;
import racingcar.domain.*;
import racingcar.entity.GameEntity;
import racingcar.entity.PlayerEntity;
import racingcar.service.dto.GameHistoryDto;
import racingcar.service.dto.RacingCarDto;
import racingcar.service.dto.RacingGameDto;
import racingcar.entity.RacingGameEntity;

import java.util.List;

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

    public GameHistoryDto playGame(final RacingGameDto racingGameDto) {
        final RacingCars racingCars = moveRacingCars(racingGameDto);

        saveRacingCars(racingGameDto, racingCars);

        return generateOneGameHistoryDto(racingCars);
    }

    private RacingCars moveRacingCars(RacingGameDto racingGameDto) {
        final List<Name> names = generateNames(racingGameDto.getNames());
        final RacingGame racingGame = new RacingGame(new RacingCars(generateRacingCars(names)), new TryCount(racingGameDto.getTrialCount()));
        return racingGame.moveCars();
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

    private void saveRacingCars(final RacingGameDto racingGameDto, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<PlayerEntity> playerEntities = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerEntity(winnerNames, racingCar))
                .collect(toList());
        final GameEntity gameEntity = new GameEntity(racingGameDto.getTrialCount(), racingGameDto.getApplicationType());
        racingGameRepository.save(new RacingGameEntity(gameEntity, playerEntities));
    }

    private PlayerEntity createPlayerEntity(final List<String> winnerNames, final RacingCar racingCar) {
        return new PlayerEntity(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }

    private GameHistoryDto generateOneGameHistoryDto(final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());

        return new GameHistoryDto(winnerNames, racingCarsDto);
    }

    public List<GameHistoryDto> findRacingGameHistory() {
        final List<RacingGameEntity> racingGameEntities = racingGameRepository.findAll();
        return generateOneGameHistoryDtos(racingGameEntities);
    }

    private List<GameHistoryDto> generateOneGameHistoryDtos(final List<RacingGameEntity> racingGameEntities) {
        return racingGameEntities.stream()
                .map(RacingGameEntity::getPlayer)
                .map(players -> new GameHistoryDto(generateWinners(players), generateRacingCarDtos(players)))
                .collect(toList());
    }

    private List<String> generateWinners(final List<PlayerEntity> playerEntities) {
        return playerEntities.stream()
                .filter(PlayerEntity::getIsWinner)
                .map(PlayerEntity::getName)
                .collect(toList());
    }

    private List<RacingCarDto> generateRacingCarDtos(final List<PlayerEntity> playerEntities) {
        return playerEntities.stream()
                .map(player -> new RacingCarDto(player.getName(), player.getPosition()))
                .collect(toList());
    }
}
