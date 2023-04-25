package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.TrackRequest;
import racingcar.controller.dto.TrackResponse;
import racingcar.service.RacingService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RacingWebController {

    private final RacingService racingService;

    public RacingWebController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> play(@Valid @RequestBody final TrackRequest trackRequest) {
        final TrackResponse trackResponse = racingService.play(trackRequest);

        return ResponseEntity.ok(trackResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<TrackResponse>> play() {
        final List<TrackResponse> trackResponses = racingService.findAll();

        return ResponseEntity.ok(trackResponses);
    }
}
