package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

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
        gameSaved.put(serial++, gameResultDto);
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
        return null;
    }

    @Override
    public Map<Long, List<WinnerEntity>> findAllWinners() {
        return null;
    }
}
