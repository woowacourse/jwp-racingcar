package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.util.NameParser;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.service.RacingGameService;

@RestController
@RequestMapping("/plays")
public class WebRacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping
    public ResponseEntity<RacingCarResponse> play(@Valid @RequestBody final RacingCarRequest racingCarRequest) {
        final RacingCarResponse racingCarResponse = racingGameService.play(
                NameParser.getSlicedName(racingCarRequest.getNames()),
                racingCarRequest.getTryCount());

        return ResponseEntity.ok().body(racingCarResponse);
    }

    @GetMapping
    public ResponseEntity<List<RacingCarResponse>> log() {
        final List<RacingCarResponse> racingCarHistory = racingGameService.findAllGameLog();

        return ResponseEntity.ok().body(racingCarHistory);
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
