package racing.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.service.RacingGameService;
import racing.util.RandomNumberGenerator;

@RestController
public class WebRacingGameController implements RacingGameController {

    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResultDto play(@RequestBody GameInputDto gameInputDto) {
        return racingGameService.playGame(gameInputDto,
            new RandomNumberGenerator());
    }

    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameResultDto> showAllGames() {
        return racingGameService.showGames();
    }
}
