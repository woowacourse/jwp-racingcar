package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingGameInputDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class RacingGameController {
    private RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays", consumes = "application/json")
    public ResponseEntity<RacingGameResultDto> play(@RequestBody final RacingGameInputDto racingGameInputDto) {
        RacingGameResultDto racingGameResultDto = racingGameService.play(racingGameInputDto);
        return ResponseEntity.ok(racingGameResultDto);
    }

    @GetMapping(path = "/plays", produces = "application/json")
    public ResponseEntity<List<RacingGameResultDto>> requestResult() {
        List<RacingGameResultDto> racingGameDtos = racingGameService.requestAllRacingGameResult();
        return ResponseEntity.ok(racingGameDtos);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
