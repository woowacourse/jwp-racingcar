package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.RacingRequest;
import racingcar.controller.dto.RacingResponse;
import racingcar.domain.RacingGame;
import racingcar.service.RacingService;

@RestController
public final class WebRacingController {

    private final RacingService racingService;

    @Autowired
    public WebRacingController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping(path = "/plays")
    public RacingResponse playRacingGame(
            @Valid @RequestBody final RacingRequest racingRequest) {
        RacingGame racingGame = this.racingService.playRacingGame(
                racingRequest.getNames(),
                racingRequest.getCount());
        return RacingResponse.from(racingGame);
    }

    @GetMapping(path = "/plays")
    public List<RacingResponse> getAllRacingGame() {
        List<RacingGame> racingGames = this.racingService.getAllRacingGame();
        return racingGames.stream()
                .map(RacingResponse::from)
                .collect(Collectors.toList());
    }
}
