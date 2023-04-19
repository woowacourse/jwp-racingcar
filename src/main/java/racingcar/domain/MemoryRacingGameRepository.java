package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MemoryRacingGameRepository implements RacingGameRepository {

    private static Long serial = 0L;

    private final Map<Long, GameResultDto> gameSaved = new HashMap<>();
    private final Map<Long, Integer> gameTimeSaved = new HashMap<>();

    @Override
    public Long save(final RacingGame racingGame) {
        final var gameResultDto = new GameResultDto(racingGame.getCars(), racingGame.winners());
        gameSaved.put(++serial, gameResultDto);
        gameTimeSaved.put(serial, racingGame.getGameTimeValue());
        return serial;
    }

    @Override
    public Optional<RacingGame> findById(final Long id) {
        final var gameResultDto = gameSaved.get(id);
        final var gameTime = gameTimeSaved.get(id);
        List<CarDto> racingCars = gameResultDto.getRacingCars();
        List<Car> cars = racingCars.stream()
                .map(carDto -> new Car(carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toList());
        return Optional.of(new RacingGame(new Cars(cars), new GameTime(gameTime)));
    }

    @Override
    public Map<Long, List<CarEntity>> findAllCars() {
        final Map<Long, List<CarEntity>> entitiesById = new HashMap<>();
        for (final Map.Entry<Long, GameResultDto> entry : gameSaved.entrySet()) {
            final var id = entry.getKey();
            final var carEntities = entry.getValue().getRacingCars().stream()
                    .map(carDto -> new CarEntity(carDto, id))
                    .collect(Collectors.toList());
            entitiesById.put(id, carEntities);
        }
        return entitiesById;
    }

    @Override
    public Map<Long, List<WinnerEntity>> findAllWinners() {
        final Map<Long, List<WinnerEntity>> entitiesById = new HashMap<>();
        for (final Map.Entry<Long, GameResultDto> entry : gameSaved.entrySet()) {
            final var id = entry.getKey();
            final var winnerEntities = Arrays.stream(entry.getValue().getWinners().split(","))
                    .map(name -> new WinnerEntity(name, id))
                    .collect(Collectors.toList());
            entitiesById.put(id, winnerEntities);
        }
        return entitiesById;
    }

    public Map<Long, GameResultDto> getGameSaved() {
        return gameSaved;
    }

    public Map<Long, Integer> getGameTimeSaved() {
        return gameTimeSaved;
    }
}
