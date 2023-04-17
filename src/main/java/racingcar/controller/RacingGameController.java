package racingcar.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import racingcar.dao.RacingCarDao;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.service.RacingCarService;

@Controller
public class RacingGameController {

    private final RacingCarDao racingCarDao;
    private final RacingCarService racingCarService;

    public RacingGameController(final RacingCarDao racingCarDao, RacingCarService racingCarService) {
        this.racingCarDao = racingCarDao;
        this.racingCarService = racingCarService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RacingCarResponse> play(@RequestBody final RacingCarRequest racingCarRequest) {
        RacingCars racingCars = racingCarService.createRacingCar(racingCarRequest);
        TryCount tryCount = new TryCount(racingCarRequest.getTryCount());

        racingCarService.playGame(racingCars, tryCount);

        racingCarDao.insertGame(racingCars, tryCount);

        final List<RacingCarDto> racingCarDtos = racingCarService.createRacingCarDtos(racingCars);

        return ResponseEntity.ok().body(
            new RacingCarResponse(racingCars.getWinnerNames(), racingCarDtos));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
