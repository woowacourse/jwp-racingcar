package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> plays(@RequestBody @Valid final GameRequestDto gameRequest) {
        final GameResponseDto saveGameResponseDto = racingGameService.play(gameRequest);
        return ResponseEntity.ok(saveGameResponseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponseDto>> findAll() {
        List<GameResponseDto> gameResponseDtos = racingGameService.findAll();
        return ResponseEntity.ok(gameResponseDtos);
    }
}
