package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameDto;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResultDto;
import racingcar.service.GameService;

@RestController
public class WebController {
    private final GameService gameService;

    public WebController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody GameRequestDto gameRequestDto) {
        final GameDto gameDto = new GameDto(gameRequestDto);
        GameResultDto response = gameService.play(gameDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/plays")
    public List<GameResultDto> showGameHistory() {
        return gameService.gameHistory();
    }
}
