package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponseDto> play(@Valid @RequestBody RacingGameRequestDto racingGameRequestDto) {
        ResultResponseDto playResult = racingCarService.play(racingGameRequestDto);
        return ResponseEntity.ok(playResult);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultResponseDto>> readGameResults() {
        List<ResultResponseDto> result = racingCarService.readGameResultAll();
        return ResponseEntity.ok(result);
    }
}
