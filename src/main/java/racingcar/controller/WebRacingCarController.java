package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.RacingGameRequest;
import racingcar.dto.response.ExceptionResponse;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.WebRacingCarService;

@RestController
public class WebRacingCarController {

    private final WebRacingCarService racingCarService;

    public WebRacingCarController(final WebRacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody @Valid RacingGameRequest racingGameRequest) {
        return ResponseEntity.ok().body(racingCarService.play(racingGameRequest));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> findGameResults() {
        return ResponseEntity.ok().body(racingCarService.findGameResults());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentValidException(MethodArgumentNotValidException exception) {
        final String exceptionMessage = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return ResponseEntity.badRequest().body(new ExceptionResponse(exceptionMessage));
    }
}
