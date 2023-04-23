package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
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
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {

    private final RacingCarService racingCarService;

    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody @Valid final RacingGameRequest racingGameRequest) {
        final RacingGameResponse racingGameResponse = racingCarService.play(racingGameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(racingGameResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> findGameResults() {
        final List<RacingGameResponse> racingGameResponses = racingCarService.findGameResults();
        return ResponseEntity.status(HttpStatus.OK).body(racingGameResponses);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentValidException(
            final MethodArgumentNotValidException exception) {
        final String exceptionMessage = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exceptionMessage));
    }
}
