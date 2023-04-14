package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarRandomNumberGenerator;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<CarGameResponse> plays(@RequestBody CarGameRequest carGameRequest) {
        CarGameResponse result = racingGameService.play(carGameRequest);
        return ResponseEntity.ok(result);
    }
}
