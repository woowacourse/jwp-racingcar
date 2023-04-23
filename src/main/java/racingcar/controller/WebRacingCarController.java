package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingStartRequest;
import racingcar.dto.response.RacingResultResponse;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebRacingCarController {

    private final RacingCarService webRacingCarService;

    public WebRacingCarController(RacingCarService webRacingCarService) {
        this.webRacingCarService = webRacingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultResponse> play(@RequestBody @Valid RacingStartRequest racingStartRequest) {
        return ResponseEntity.ok(webRacingCarService.play(racingStartRequest));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResultResponse>> historyInquiry() {
        return ResponseEntity.ok().body(webRacingCarService.inquireHistory());
    }
}
