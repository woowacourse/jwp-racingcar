package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.request.PlayRequest;
import racingcar.response.PlayResponse;
import racingcar.service.GameService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/plays")
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<PlayResponse> plays(@RequestBody @Valid final PlayRequest playRequest) {
        PlayResponse playResponse = gameService.playRacing(playRequest.getNames(), playRequest.getCount());
        return ResponseEntity.ok().body(playResponse);
    }

    @GetMapping
    public ResponseEntity<List<PlayResponse>> plays() {
        List<PlayResponse> allGameHistory = gameService.showGameHistory();
        return ResponseEntity.ok().body(allGameHistory);
    }
}
