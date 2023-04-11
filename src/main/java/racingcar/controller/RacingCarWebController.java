package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.AttemptNumber;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingCarGame;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class RacingCarWebController {

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> playRacingCar(@RequestBody RequestDto requestDto) {
        final Cars cars = Cars.from(List.of(requestDto.getNames().split(",")));
        final AttemptNumber attemptNumber = new AttemptNumber(requestDto.getCount());
        final RacingCarGame racingCarGame = new RacingCarGame(cars, attemptNumber, new RandomNumberGenerator());

        final List<String> winners = racingCarGame.findWinners();
        final String concatWinners = String.join(",", winners);
        final List<Car> racingCars = racingCarGame.getCars().getCars();
        final ResponseDto responseDto = new ResponseDto(concatWinners, racingCars);

        return ResponseEntity.ok().body(responseDto);
    }
}
