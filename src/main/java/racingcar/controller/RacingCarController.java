package racingcar.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.util.TextParser;

@RestController
public class RacingCarController {

    private static final String CAR_NAMES_DELIMITER = ",";
    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponseDto> play(@Valid @RequestBody PlayRequestDto playRequestDto) {
        final int count = playRequestDto.getCount();
        final List<String> carNames = TextParser.parseByDelimiter(playRequestDto.getNames(), CAR_NAMES_DELIMITER);

        final PlayResponseDto playResult = racingCarService.playGame(count, carNames);

        return ResponseEntity.ok(playResult);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponseDto>> records() {
        final List<PlayResponseDto> records = racingCarService.findAllPlayRecords();
        return ResponseEntity.ok(records);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors().stream()
                .filter(error -> Objects.nonNull(error.getDefaultMessage()))
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(), DefaultMessageSourceResolvable::getDefaultMessage)
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handle(Exception exception) {
        return exception.getMessage();
    }
}
