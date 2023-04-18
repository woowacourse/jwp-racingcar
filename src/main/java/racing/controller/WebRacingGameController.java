package racing.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.service.RacingGameService;
import racing.util.RandomNumberGenerator;

@Controller
public class WebRacingGameController implements RacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @ResponseBody
    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResultDto play(@RequestBody GameInputDto gameInputDto) {
        return racingGameService.playGame(gameInputDto,
            new RandomNumberGenerator());
    }

    @ResponseBody
    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameResultDto> showAllGames() {
        return racingGameService.showGames();
    }
}
