package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
        String bindingErrorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(bindingErrorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
