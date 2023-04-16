package racingcar.controller;

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

import java.util.List;

@Controller
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultResponse>> searchAllHistories() {
        List<ResultResponse> allHistories = racingCarService.searchAllGame();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(allHistories);
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> play(@RequestBody final NamesAndCountRequest namesAndCount) {
        ResultResponse resultResponse = racingCarService.playGame(namesAndCount);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.badRequest().body("요청이 올바르지 않습니다.");
    }
}
