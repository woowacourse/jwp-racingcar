package racingcar.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Component;
import racingcar.domain.RacingCars;

@Component
public class RacingGameMapper {

    public GameResponse toGameResponse(final RacingCars racingCars) {
        final String winnerName = String.join(", ", racingCars.getWinnerNames());

        final List<RacingCarDto> racingCarsDto = racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .collect(toList());

        return new GameResponse(winnerName, racingCarsDto);
    }
}
