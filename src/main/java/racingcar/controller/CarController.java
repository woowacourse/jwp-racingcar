package racingcar.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class CarController {

    @PostMapping("/plays")
    public RacingResultResponse play(@RequestBody RacingCarRequest request) {
        String names = request.getNames();
        int count = request.getCount();

        RacingCars racingCars = Arrays.stream(names.split(","))
                .map(Car::new)
                .collect(collectingAndThen(toList(), RacingCars::new));

        RacingGame racingGame = new RacingGame(new RandomNumberGenerator(), racingCars);
        racingGame.moveCars(count);
        List<String> winners = racingGame.getWinners();

        List<RacingCarDto> racingCarDtos = racingCars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName(), car.getPosition()))
                .collect(toList());

        return new RacingResultResponse(winners, racingCarDtos);
    }
}
