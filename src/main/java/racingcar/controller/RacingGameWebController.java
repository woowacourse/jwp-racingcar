package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public RacingGameResponseDto run(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        return racingGameService.run(racingGameRequestDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
