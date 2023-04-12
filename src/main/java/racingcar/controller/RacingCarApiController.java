package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.RacingCarRequestDto;
import racingcar.dto.response.RacingCarResponseDto;
import racingcar.dto.response.RacingGameResponseDto;

@RestController
public class RacingCarApiController {

    @PostMapping("/plays")
    public RacingGameResponseDto play(@RequestBody RacingCarRequestDto racingCarRequestDto) {
        final List<String> names = Arrays.stream(racingCarRequestDto.getNames().split(","))
                .collect(Collectors.toList());
        final Cars cars = new Cars(names);
        final Count count = new Count(racingCarRequestDto.getCount());
        final RacingGame racingGame = new RacingGame(new RandomNumberGenerator(), cars, count);
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        final List<String> winners = racingGame.findWinners();
        StringBuilder stringBuilder = new StringBuilder();
        for (final String winner : winners) {
            stringBuilder.append(winner);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        List<RacingCarResponseDto> racingCarResponseDtos = new ArrayList<>();

        final List<Car> currentResult = racingGame.getCurrentResult();
        for (final Car car : currentResult) {
            racingCarResponseDtos.add(new RacingCarResponseDto(car.getName(), car.getPosition()));
        }

        return new RacingGameResponseDto(stringBuilder.toString(), racingCarResponseDtos);
    }
}
