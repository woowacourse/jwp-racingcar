package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingCarController {
    private static final String SPLIT_DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebRacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultResponse> play(@Valid @RequestBody RacingCarRequest request) {
        List<String> carNames = getCarNames(request.getNames());
        int gameId = racingCarService.playRacingGame(carNames, request.getCount());
        RacingResultResponse racingResultResponse = racingCarService.obtainRacingResult(gameId);
        return ResponseEntity.ok().body(racingResultResponse);
    }

    private List<String> getCarNames(String names) {
        return List.of(names.split(SPLIT_DELIMITER));
    }
}
