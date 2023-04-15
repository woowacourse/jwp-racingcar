package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingGameService;
import racingcar.util.RandomNumberGenerator;

import java.util.List;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingResultResponseDto> play(@RequestBody GameInputDto gameInputDto) {
        RacingResultResponseDto racingResultResponseDto = racingGameService.playGameWithoutPrint(gameInputDto, new RandomNumberGenerator());
        return ResponseEntity.ok(racingResultResponseDto);
    }
    
    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingResultResponseDto>> findAll() {
        return ResponseEntity.ok(racingGameService.findAllGameResult());
    }
}
