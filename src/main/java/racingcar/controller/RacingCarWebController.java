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
        final RacingCarGame racingCarGame = getRacingCarGame(requestDto);
        final ResponseDto responseDto = getResponseDto(racingCarGame);
        return ResponseEntity.ok().body(responseDto);
    }

    private RacingCarGame getRacingCarGame(final RequestDto requestDto) {
        final Cars cars = Cars.from(List.of(requestDto.getNames().split(",")));
        final AttemptNumber attemptNumber = new AttemptNumber(requestDto.getCount());
        return new RacingCarGame(cars, attemptNumber, new RandomNumberGenerator());
    }

    private ResponseDto getResponseDto(final RacingCarGame racingCarGame) {
        final List<String> winners = racingCarGame.findWinners();
        final String concatWinners = String.join(",", winners);
        final List<Car> racingCars = racingCarGame.getCars();
        return new ResponseDto(concatWinners, racingCars);
    }
}
