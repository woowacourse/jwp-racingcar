package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.RacingGameResultResponse;
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {

    private final RacingCarService racingCarService;

    public WebRacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }


    //TODO: count에 int default value가 들어가는 문제
    @PostMapping("/plays")
    public ResponseEntity<RacingGameResultResponse> play(@RequestBody RacingGameRequest racingGameRequest) {
        return ResponseEntity.ok().body(racingCarService.play(racingGameRequest));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResultResponse>> findGameResults() {
        return ResponseEntity.ok().body(racingCarService.findGameResults());
    }
}
