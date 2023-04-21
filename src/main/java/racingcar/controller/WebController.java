package racingcar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.service.RacingResponse;
import racingcar.service.RacingcarService;

@RestController
public class WebController {
    private final RacingcarService racingcarService;

    public WebController(RacingcarService racingcarService) {
        this.racingcarService = racingcarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingCarResponse> plays(@Valid @RequestBody RacingCarRequest request) {
        List<String> carNames = InputConvertor.carNames(request.getNames());
        Cars cars = Cars.from(carNames);
        int tryCount = InputConvertor.tryCount(request.getCount());
        Trial trial = new Trial(tryCount);
        return ResponseEntity.ok()
            .body(racingcarService.play(cars, trial));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResponse>> allResults() {
        return ResponseEntity.ok()
            .body(racingcarService.allResults());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
            .body(exception.getMessage());
    }
}
