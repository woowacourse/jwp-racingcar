package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@Component
public class RacingGameMapper {

    public GameEntity toGameEntity(final int trial) {
        return new GameEntity(trial);
    }

    public List<CarEntity> toCarEntities(final RacingGame racingGame, final int gameId) {
        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        return racingGame.getCars().stream()
                .map(car -> CarEntity.of(car, winners.contains(car.getName()), gameId))
                .collect(toList());
    }

    public List<GamePlayResponseDto> toGamePlayResponseDtos(final List<CarEntity> cars) {
        final Map<Integer, List<CarEntity>> gameIdByCars = cars.stream()
                .collect(Collectors.groupingBy(CarEntity::getGameId));

        return gameIdByCars.values().stream()
                .map(this::mapToGamePlayResponseDto)
                .collect(toList());
    }

    private GamePlayResponseDto mapToGamePlayResponseDto(final List<CarEntity> carEntities) {
        final List<String> winners = carEntities.stream()
                .filter(CarEntity::isWinner)
                .map(CarEntity::getName)
                .collect(toList());
        final List<CarDto> carDtos = carEntities.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(toList());
        return new GamePlayResponseDto(winners, carDtos);
    }
}
