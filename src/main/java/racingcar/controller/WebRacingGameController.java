package racingcar.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.ExceptionResponseDto;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingGameService;
import racingcar.util.RandomNumberGenerator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingResultResponseDto> play(@RequestBody @Valid GameInputDto gameInputDto) {
        RacingResultResponseDto racingResultResponseDto = racingGameService.playGameWithoutPrint(gameInputDto, new RandomNumberGenerator());
        return ResponseEntity.ok(racingResultResponseDto);
    }
    
    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingResultResponseDto>> findAll() {
        return ResponseEntity.ok(racingGameService.findAllGameResult());
    }
}
