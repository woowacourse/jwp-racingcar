package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;

@RestController
public final class RacingController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<GameResultDto> playRacingGame(
            @Valid @RequestBody final RacingGameRequestDto racingGameRequestDto
    ) {
        return ResponseEntity.ok(racingGameService.playRacingGame(racingGameRequestDto));
    }
}
