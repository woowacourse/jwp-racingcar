package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.controller.dto.GameStartRequest;
import racingcar.controller.dto.GameResultReponse;
import racingcar.service.RacingCarService;

import java.util.List;

@Controller
public class WebController {

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResultReponse>> searchAllHistories() {
        List<GameResultReponse> allHistories = racingCarService.searchAllGame();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allHistories);
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultReponse> play(@RequestBody final GameStartRequest namesAndCount) {
        GameResultReponse gameResultReponse = racingCarService.playGame(namesAndCount);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gameResultReponse);
    }
}
