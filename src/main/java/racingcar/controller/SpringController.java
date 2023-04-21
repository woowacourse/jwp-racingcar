package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;

@RestController
@RequestMapping("/plays")
public class SpringController {

    private final RacingGameService racingGameService;

    public SpringController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping
    public ResponseEntity<GameResponseDto> postInput(@RequestBody GameRequestDto gameRequestDto) {
        GameResponseDto gameResponseDto = racingGameService.play(gameRequestDto);
        return ResponseEntity.ok()
                .body(gameResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDto>> getWinner() {
        return ResponseEntity.ok()
                .body(racingGameService.getGameLog());
    }
}
