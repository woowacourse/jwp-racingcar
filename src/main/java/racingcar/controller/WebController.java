package racingcar.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.service.RacingResponse;
import racingcar.service.RacingcarService;

@RestController
public class WebController {

    private final RacingcarService racingcarService;

    public WebController(RacingcarService racingcarService) {
        this.racingcarService = racingcarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResponse> plays(@RequestBody RacingGameRequest request) {
        List<String> carNames = InputConvertor.carNames(request.getNames());
        int tryCount = InputConvertor.tryCount(request.getCount());
        return ResponseEntity.ok()
            .body(racingcarService.move(carNames, tryCount));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingResponse>> allResults(){
        return ResponseEntity.ok()
            .body(racingcarService.allResults());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
            .body(exception.getMessage());
    }
}
