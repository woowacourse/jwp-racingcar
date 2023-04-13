package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingGameController {

    private final RacingCarService racingCarService;

    public RacingGameController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> startGame(@RequestBody final StartGameRequestDto request) {
        Cars cars = racingCarService.makeCars(request.getNames());
        TryCount tryCount = racingCarService.makeTryCount(request.getCount());

        return new ResponseEntity<>(racingCarService.startRace(cars, tryCount), HttpStatus.OK);
    }
}
