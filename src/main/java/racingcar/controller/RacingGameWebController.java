package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

import java.util.List;

@RequestMapping("/plays")
@RestController
public class RacingGameWebController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final RacingCarService racingCarService;

    public RacingGameWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping
    public ResponseEntity<List<GameHistoriesResponseDto>> findGameHistories() {
        return ResponseEntity.ok()
                .body(racingCarService.findAllGameHistories());
    }

    @PostMapping
    public ResponseEntity<GameResultResponseDto> startGame(@RequestBody final StartGameRequestDto request) {
        Cars cars = makeCars(request.getNames());
        TryCount tryCount = makeTryCount(request.getCount());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(racingCarService.startRace(cars, tryCount));
    }

    public Cars makeCars(final String input) {
        List<String> carNames = List.of(input.split(CAR_NAME_DELIMITER));
        return Cars.from(carNames);
    }

    public TryCount makeTryCount(final int input) {
        return new TryCount(input);
    }
}
