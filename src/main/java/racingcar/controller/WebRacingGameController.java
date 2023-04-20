package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.service.RacingGameService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public final class WebRacingGameController {

    private final RacingGameService racingGameService;

    @Autowired
    public WebRacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public ResponseEntity<GameResultResponse> playRacingGame(
            @Valid @RequestBody final RacingGameRequest racingGameRequest
    ) {
        return ResponseEntity.ok(racingGameService.playRacingGame(racingGameRequest));
    }

    @GetMapping(path = "/plays")
    public ResponseEntity<List<GameResultResponse>> readRecords() {
        List<RacingGame> allGames = racingGameService.makeGameRecords();
        List<GameResultResponse> gameResultResponses = allGames.stream()
                .map(this::toGameResultResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(gameResultResponses);
    }

    private GameResultResponse toGameResultResponse(final RacingGame racingGame) {
        return new GameResultResponse(
                toWinnerResponse(racingGame),
                toCarDataResponse(racingGame)
        );
    }

    private List<CarData> toCarDataResponse(final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .map(car -> new CarData(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String toWinnerResponse(final RacingGame racingGame) {
        return racingGame.getWinners()
                .stream()
                .map(Car::getCarName)
                .reduce((o1, o2) -> o1 + "," + o2)
                .orElseThrow();
    }
}
