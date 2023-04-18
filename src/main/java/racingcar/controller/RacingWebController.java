package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingService;

import java.util.List;

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

        return ResponseEntity.ok(trackResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<TrackResponse>> play() {
        List<TrackResponse> trackResponses = racingService.findAll();
        return ResponseEntity.ok(trackResponses);
    }
}
