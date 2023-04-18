package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.ExceptionDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingGameWebController {

    private final static String NAME_DELIMITER = ",";

    private final RacingGameService racingGameService;

    public RacingGameWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDto> run(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        List<String> names = Arrays.asList(racingGameRequestDto.getNames().split(NAME_DELIMITER));
        int count = racingGameRequestDto.getCount();
        RacingGameResponseDto racingGameResponseDto = racingGameService.run(names, count);
        return ResponseEntity.status(HttpStatus.OK).body(racingGameResponseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponseDto>> getAllResults() {
        List<RacingGameResponseDto> results = racingGameService.findAllResults();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handle(IllegalArgumentException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handle(Exception e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDto);
    }
}
