package racingcar.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;
import racingcar.service.RacingCarService;

@Controller
public class WebController {

    private final RacingCarService racingCarService;

    public WebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> postPlays(@RequestBody final NamesAndCountRequest namesAndCount) {
        ResultResponse resultResponse = racingCarService.playGame(namesAndCount);
        return ResponseEntity.created(URI.create("/plays"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultResponse>> getPlays() {
        List<ResultResponse> resultResponses = racingCarService.findAllResult();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponses);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
