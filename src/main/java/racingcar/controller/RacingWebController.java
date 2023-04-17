package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingService;

@RestController
public class RacingWebController {

    private final RacingService racingService;

    public RacingWebController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> play(@RequestBody final TrackRequest trackRequest) {
        final String names = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        TrackResponse trackResponse = racingService.play(names, trialTimes);

        return play(trackResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<TrackResponse> play(@RequestBody final TrackResponse trackResponse) {
        return ResponseEntity.ok(trackResponse);
    }
}
