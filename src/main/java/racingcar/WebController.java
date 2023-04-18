package racingcar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final GameService gameService;

    public WebController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        final GameDto gameDto = new GameDto(playRequestDto);
        GameResultDto response = gameService.play(gameDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/plays")
    public GameResultsDto showGameHistory() {
        return gameService.gameHistory();
    }
}
