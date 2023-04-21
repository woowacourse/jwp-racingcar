package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingCarController {
    private final RacingCarService racingCarService;
    
    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }
    
    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody @Valid GameRequestDto gameRequestDto) {
        final GameResponseDto gameResponseDto = racingCarService.playGame(gameRequestDto, new RandomBasedMoveStrategy());
        return ResponseEntity.ok(gameResponseDto);
    }
    
    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameResponseDto>> findAll() {
        return ResponseEntity.ok(racingCarService.findAllGameResult());
    }
}
