package racingcar.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import racingcar.dto.RacingResultDTO;
import racingcar.dto.RacingStartDTO;
import racingcar.service.CarRacingService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarRacingWebController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final CarRacingService carRacingService;

    public CarRacingWebController(final CarRacingService carRacingService) {
        this.carRacingService = carRacingService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultDTO> play(@Valid @RequestBody RacingStartDTO racingStartDTO) {
        RacingResultDTO racingResultDTO = carRacingService.play(racingStartDTO.getNames(), racingStartDTO.getCount());
        return ResponseEntity.ok(racingResultDTO);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultDTO>> showGameResult() {
        final List<RacingResultDTO> gameResults = carRacingService.showGameResults();
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
            log.error("Unexpected error occurred : {}", errorMessage, exception);
        });

        return ResponseEntity.badRequest().body(errorMessageByFields);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormat(InvalidFormatException exception) {
        final String exceptionMessage = "[ERROR] 시도할 횟수에 숫자를 입력할 수 없습니다. 입력 값 : " + exception.getValue();
        log.error("Unexpected error occurred : {}", exceptionMessage, exception);
        return ResponseEntity.badRequest().body(exceptionMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException exception) {
        log.error("Unexpected error occurred : {}", exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
