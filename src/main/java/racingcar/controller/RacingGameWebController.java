package racingcar.controller;

import java.util.List;
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
import racingcar.utils.CarsFactory;

@RestController
public class RacingGameWebController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final RacingCarService racingCarService;

    public RacingGameWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> startGame(@RequestBody final StartGameRequestDto request) {
        Cars cars = makeCars(request.getNames());
        TryCount tryCount = makeTryCount(request.getCount());

        return new ResponseEntity<>(racingCarService.startRace(cars, tryCount), HttpStatus.OK);
    }

    public Cars makeCars(final String input) {
        List<String> carNames = List.of(input.split(CAR_NAME_SEPARATOR));
        return CarsFactory.createCars(carNames);
    }

    public TryCount makeTryCount(final int input) {
        return new TryCount(input);
    }
}
