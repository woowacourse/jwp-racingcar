package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

@RestController
public class RaceController {

    private final RaceService raceService;

    public RaceController(final RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RaceResponse> play(@RequestBody @Valid final RaceRequest raceRequest) {
        return ResponseEntity.ok()
            .body(raceService.play(raceRequest));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RaceResponse>> findAllRace() {
        return ResponseEntity.ok()
            .body(raceService.findAllRace());
    }
}
