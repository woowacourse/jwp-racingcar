package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
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

    @GetMapping("/plays")
    public ResponseEntity<List<TrackResponse>> getResult() {
        List<TrackResponse> allResults = racingService.findAllResults();
        return ResponseEntity.ok(allResults);
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> play(@RequestBody @Valid final TrackRequest trackRequest) {
        final String names = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        TrackResponse trackResponse = racingService.play(names, trialTimes);

        return ResponseEntity.ok(trackResponse);
    }
}
