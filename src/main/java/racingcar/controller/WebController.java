package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.services.GameService;

@RestController
public class WebController {

    private final GameService gameService;

    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultDto> play(@RequestBody GameInfoDto gameInfoDto) {
        try {
            ResultDto resultDto = gameService.play(gameInfoDto);
            return ResponseEntity.ok().body(resultDto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
