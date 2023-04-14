package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.RacingGameRequest;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.CarGroup;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> playRacingGame(@RequestBody RacingGameRequest request) {
        final CarGroup carGroup = new CarGroup(request.getNames());
        final RacingGameResponse response = racingGameService.race(carGroup, request.getCount());

        return ResponseEntity.ok(response);
    }
}
