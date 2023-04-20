package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingCarGameRequestDto;
import racingcar.dto.RacingCarGameResultResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingCarGameResultResponseDto> play(@Valid @RequestBody RacingCarGameRequestDto racingCarGameRequestDto) {
        RacingCarGameResultResponseDto playResult = racingCarService.play(racingCarGameRequestDto);
        return ResponseEntity.ok(playResult);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingCarGameResultResponseDto>> readGameResults() {
        List<RacingCarGameResultResponseDto> result = racingCarService.readGameResultAll();
        return ResponseEntity.ok(result);
    }
}
