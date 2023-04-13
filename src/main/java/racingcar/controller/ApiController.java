package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.Lap;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.WinnerMaker;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.dto.request.GameResultDto;
import racingcar.service.GameService;

import java.util.List;

@RestController
public class ApiController {
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final GameService gameService;

    @Autowired
    public ApiController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto request) {
        final Cars cars = new Cars(request.getNames());
        final Lap lap = new Lap(request.getCount());
        race(cars, lap, new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER));
        final WinnerMaker winnerMaker = new WinnerMaker();
        List<String> winners = winnerMaker.getWinnerCarsName(cars.getLatestResult());
        final GameResultDto gameResult = GameResultDto.of(winners, request.getCount(), cars.getLatestResult());
        GameResponseDto gameResponseDto = gameService.playGame(gameResult);
        return new ResponseEntity<>(gameResponseDto, HttpStatus.CREATED);
//        return ResponseEntity.created(URI.create("/plays")).body(response);
    }

    private void race(Cars cars, Lap lap, NumberGenerator numberGenerator) {
        while (!lap.isFinish()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
    }
}
