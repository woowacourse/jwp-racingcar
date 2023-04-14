package racingcar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;
import racingcar.service.GameService;

@RestController
public class RacingGameController {

    private final GameService gameService;

    @Autowired
    public RacingGameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResultDto play(@RequestBody final GameInputDto gameInputDto) {
        return gameService.playGame(gameInputDto);
    }

    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameResultDto> fetchHistory() {
        return gameService.fetchHistory();
    }

    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
