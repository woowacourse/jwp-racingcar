package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.gameInitializationRequest;
import racingcar.dto.gameResultResponse;
import racingcar.service.MainRacingCarService;

@RestController
public class RacingCarWebController {

    private final MainRacingCarService mainRacingCarService;

    @Autowired
    public RacingCarWebController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<gameResultResponse> playRacingCar(@RequestBody gameInitializationRequest gameInitializationRequest) {
        final gameResultResponse gameResultResponse = mainRacingCarService.raceCar(gameInitializationRequest);
        return ResponseEntity.ok().body(gameResultResponse);
    }
}
