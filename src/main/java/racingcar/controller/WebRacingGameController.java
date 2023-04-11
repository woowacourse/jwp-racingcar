package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.GameInfoDto;
import racingcar.dto.GameResultDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingGameController {

    @PostMapping("/plays")
    public ResponseEntity playGame(@RequestBody GameInfoDto request) {
        List<Name> names = Arrays.stream(request.getNames().split(","))
                .map(Name::new)
                .collect(Collectors.toList());

        RacingGame racingGame = new RacingGame(names);
        TryCount trycount = new TryCount(request.getCount());

        for (int i = 0; i < trycount.getCount(); i++) {
            racingGame.moveCars();
        }

        String winners = racingGame.decideWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            carDtoList.add(new CarDto(car.getName(), car.getPosition()));
        }

        return ResponseEntity.ok(new GameResultDto(winners, carDtoList));
    }
}
