package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.BadResponseDto;
import racingcar.dto.response.GameResponseDto;
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
    public ResponseEntity<List<GameResponseDto>> gameList() {
        List<GameResponseDto> allGames = racingGameService.findAllGame();
        return ResponseEntity.ok(allGames);
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> gameSave(@RequestBody GameRequestDto request) {
        GameResponseDto response = racingGameService.saveGamePlay(request.getNames(), request.getCount());

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BusinessArgumentException.class)
    public ResponseEntity<BadResponseDto> handleException(BusinessArgumentException e) {
        return ResponseEntity.badRequest().body(new BadResponseDto(e.getErrorCodeStatus(), e.getMessage()));
    }
}
