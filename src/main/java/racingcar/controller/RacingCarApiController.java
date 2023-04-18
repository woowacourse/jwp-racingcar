package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingCarRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarApiController {

    private final RacingCarService racingCarService;

    public RacingCarApiController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody RacingCarRequest racingCarRequest) {
        final RacingGameResponse result = racingCarService.play(racingCarRequest.getNames(), racingCarRequest.getCount());
        return ResponseEntity.ok(result);
    }
}
