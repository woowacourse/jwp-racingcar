package racingcar.controller.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.TrackRequest;
import racingcar.controller.TrackResponse;
import racingcar.service.RacingService;

@RestController
public class RacingWebController {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final RacingService racingService;

    public RacingWebController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<TrackResponse>> getAllResults() {
        List<TrackResponse> allResults = racingService.findAllResults();
        return ResponseEntity.ok(allResults);
    }

    @GetMapping("/plays/{id}")
    public ResponseEntity<TrackResponse> getResult(@PathVariable int id) {
        TrackResponse trackResponse = racingService.findById(id);
        return ResponseEntity.ok(trackResponse);
    }

    @PostMapping("/plays")
    public ResponseEntity<TrackResponse> play(@RequestBody @Valid final TrackRequest trackRequest) {
        final List<String> names = Arrays.stream(trackRequest.getNames().split(NAME_SPLIT_DELIMITER))
                .collect(Collectors.toList());
        final Integer trialTimes = trackRequest.getCount();
        TrackResponse trackResponse = racingService.play(names, trialTimes);

        return ResponseEntity.ok(trackResponse);
    }
}
