package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.service.MainRacingCarService;

@RestController
public class RacingCarWebController {

    private final MainRacingCarService mainRacingCarService;

    @Autowired
    public RacingCarWebController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponse> playRacingCar(@RequestBody GameInitializationRequest gameInitializationRequest) {
        final GameResultResponse gameResultResponse = mainRacingCarService.raceCar(gameInitializationRequest);
        return ResponseEntity.ok().body(gameResultResponse);
    }
}
