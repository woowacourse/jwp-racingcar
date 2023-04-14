package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.RacingInfoRequest;
import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameServiceImpl;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameServiceImpl = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingInfoResponse> playRacingGame(@RequestBody RacingInfoRequest request) {
        final CarGroup carGroup = new CarGroup(request.getNames());
        final RacingInfoResponse response = racingGameServiceImpl.race(carGroup, request.getCount());

        return ResponseEntity.ok(response);
    }
}
