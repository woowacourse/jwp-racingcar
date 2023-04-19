package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingResultResponse;
import racingcar.service.WebRacingCarService;

import java.util.List;

@RestController
public class WebRacingCarController {

    private final WebRacingCarService webRacingCarService;

    public WebRacingCarController(WebRacingCarService webRacingCarService) {
        this.webRacingCarService = webRacingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultResponse> play(@RequestBody RacingStartRequest racingStartRequest) {
        return ResponseEntity.ok(webRacingCarService.play(racingStartRequest));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultResponse>> historyInquiry() {
        return ResponseEntity.ok().body(webRacingCarService.inquireHistory());
    }
}
