package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.WebRacingCarService;

import javax.validation.Valid;

@RestController
public class WebRacingCarController {
    private final WebRacingCarService webRacingCarService;
    
    public WebRacingCarController(final WebRacingCarService webRacingCarService) {
        this.webRacingCarService = webRacingCarService;
    }
    
    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody @Valid GameRequestDto gameRequestDto) {
        final GameResponseDto gameResponseDto = webRacingCarService.playGame(gameRequestDto, new RandomBasedMoveStrategy());
        return ResponseEntity.ok(gameResponseDto);
    }
}
