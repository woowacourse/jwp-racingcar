package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.ExceptionDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    public RacingGameWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDto> run(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        RacingGameResponseDto racingGameResponseDto = racingGameService.run(racingGameRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(racingGameResponseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handle(IllegalArgumentException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }
}
