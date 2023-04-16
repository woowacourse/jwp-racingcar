package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.request.GameRequest;
import racingcar.dto.response.BadResponse;
import racingcar.dto.response.GameResponse;
import racingcar.exception.BusinessArgumentException;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    @Autowired
    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameResponse>> gameList() {
        List<GameResponse> allGames = racingGameService.findAllGame();
        return ResponseEntity.ok(allGames);
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> gamePlaySave(@RequestBody GameRequest request) {
        GameResponse response = racingGameService.saveGamePlay(request.getNames(), request.getCount());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BusinessArgumentException.class)
    public ResponseEntity<BadResponse> handleException(BusinessArgumentException e) {
        return ResponseEntity.badRequest().body(new BadResponse(e.getErrorCodeStatus(), e.getMessage()));
    }
}
