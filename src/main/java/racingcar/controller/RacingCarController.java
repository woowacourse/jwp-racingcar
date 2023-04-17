package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.GameInfoRequestDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.exception.PlayerNumberException;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> play(@RequestBody GameInfoRequestDto gameInfoRequestDto) {
        List<String> carNames = Arrays.asList(gameInfoRequestDto.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        int count = gameInfoRequestDto.getCount();
        play(cars, count, numberGenerator);
        GameResultResponseDto gameResultResponseDto = GameResultResponseDto.createGameResultResponseDto(cars.findWinners(), cars.getCars());
        racingCarService.saveResult(count, cars);
        return ResponseEntity.ok().body(gameResultResponseDto);
    }

    @ExceptionHandler({PlayerNumberException.class, PlayerSizeException.class})
    public ResponseEntity<String> handlePlayerNumber(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
