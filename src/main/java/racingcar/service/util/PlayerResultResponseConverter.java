package racingcar.service.util;

import racingcar.domain.Cars;
import racingcar.entity.PlayerResult;
import racingcar.service.dto.PlayerResultResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerResultResponseConverter {

    public static List<PlayerResultResponseDto> convertByEntities(final List<PlayerResult> playerResults) {
        return playerResults.stream()
                .map(playerResult -> new PlayerResultResponseDto(
                        playerResult.getName(),
                        playerResult.getFinalPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<PlayerResultResponseDto> convertByCars(final Cars cars) {
        return cars.getLatestResult().stream()
                .map(car -> new PlayerResultResponseDto(
                        car.getCarName().getName(),
                        car.getCurrentPosition().getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
