package racingcar.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingResultDTO;
import racingcar.dto.RacingStartDTO;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultDTO> play(@Valid @RequestBody RacingStartDTO racingStartDTO) {
        RacingResultDTO racingResultDTO = racingCarService.play(racingStartDTO.getNames(), racingStartDTO.getCount());
        return ResponseEntity.ok(racingResultDTO);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultDTO>> showGameResult() {
        final List<RacingResultDTO> gameResults = racingCarService.showGameResults();
        return ResponseEntity.ok(gameResults);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, String> errorMessageByFields = new HashMap<>();

        final List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        allErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessageByFields.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageByFields);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormat(InvalidFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[ERROR] 시도할 횟수에 숫자를 입력할 수 없습니다. 입력 값 : " + exception.getValue());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
