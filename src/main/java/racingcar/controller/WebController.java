package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingResultDTO;
import racingcar.dto.RacingStartDTO;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebController {

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultDTO> createGameAndPlay(@Valid @RequestBody RacingStartDTO racingStartDTO) {
        RacingResultDTO racingResultDTO = racingCarService.play(racingStartDTO.getNames(), racingStartDTO.getCount());
        return ResponseEntity.ok(racingResultDTO);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultDTO>> showGameResult() {
        final List<RacingResultDTO> gameResults = racingCarService.showGameResults();
        return ResponseEntity.ok(gameResults);
    }
}
