package racingcar.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ResultResponse;
import racingcar.service.PlayService;
import racingcar.service.ResultService;

@RestController
public class WebController {

    private final PlayService playService;
    private final ResultService resultService;

    public WebController(final PlayService playService, final ResultService resultService) {
        this.playService = playService;
        this.resultService = resultService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponse> postPlays(@Valid @RequestBody final NamesAndCountRequest namesAndCount) {
        ResultResponse resultResponse = playService.playGame(namesAndCount);
        return ResponseEntity.created(URI.create("/plays"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultResponse>> getPlays() {
        List<ResultResponse> resultResponses = resultService.findAllResult();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultResponses);
    }
}
