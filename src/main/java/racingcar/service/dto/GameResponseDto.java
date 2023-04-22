package racingcar.service.dto;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultResponseDto> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResultResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto createByEntity(final Game game, final List<PlayerResult> playerResults) {
        final List<PlayerResultResponseDto> racingCars = playerResults.stream()
                .map(PlayerResultResponseDto::createByEntity)
                .collect(Collectors.toUnmodifiableList());
        return new GameResponseDto(game.getWinners(), racingCars);
    }

    public static GameResponseDto createByDomain(final String winners, final Cars cars) {
        final List<PlayerResultResponseDto> racingCars = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            racingCars.add(PlayerResultResponseDto.createByDomain(car));
        }
        return new GameResponseDto(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultResponseDto> getRacingCars() {
        return racingCars;
    }
}
