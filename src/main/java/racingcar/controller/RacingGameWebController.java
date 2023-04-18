package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.ExceptionDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingGameWebController {

    public final static String NAME_DELIMITER = ",";

    private final RacingGameService racingGameService;

    public RacingGameWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    @ResponseStatus(HttpStatus.OK)
    public RacingGameResponseDto run(@RequestBody RacingGameRequestDto racingGameRequestDto) {
        List<String> names = Arrays.asList(racingGameRequestDto.getNames().split(NAME_DELIMITER));
        int count = racingGameRequestDto.getCount();
        return racingGameService.run(names, count);
    }

    @GetMapping("/plays")
    @ResponseStatus(HttpStatus.OK)
    public List<RacingGameResponseDto> getAllResults() {
        return racingGameService.findAllResults();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(IllegalArgumentException e) {
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(Exception e) {
        return new ExceptionDto(e.getMessage());
    }
}
