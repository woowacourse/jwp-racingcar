package racingcar.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    @Autowired
    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public RacingGameResponseDto playGameByRequest(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        return racingGameService.run(racingGameRequestDto);
    }

    @GetMapping("/plays")
    public List<RacingGameResponseDto> findAllResultOfGame() {
        return racingGameService.findAllGameResult();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
