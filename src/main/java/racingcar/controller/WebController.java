package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.request.PlayRequest;
import racingcar.response.PlayResponse;
import racingcar.service.GameService;

@RestController
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public PlayResponse plays(@RequestBody final PlayRequest playRequest) {
        return gameService.playRacing(playRequest.getNames(), playRequest.getCount());
    }
}
