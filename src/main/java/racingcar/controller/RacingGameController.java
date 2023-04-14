package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingGameService;
import racingcar.util.RandomNumberGenerator;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingResultDto> play(@RequestBody GameInputDto gameInputDto) {
        RacingResultDto racingResultDto = racingGameService.playGameWithoutPrint(gameInputDto, new RandomNumberGenerator());
        return ResponseEntity.ok(racingResultDto);
    }
}
