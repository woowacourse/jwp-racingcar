package racingcar.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;
import racingcar.service.GameService;

@RequestMapping("/plays")
public class RacingGameController {

    private final GameService gameService;

    public RacingGameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GameResultDto play(@RequestBody final GameInputDto gameInputDto) {
        return gameService.playGame(gameInputDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GameResultDto> fetchHistory() {
        return gameService.fetchHistory();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
