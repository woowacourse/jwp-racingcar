package racing.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.web.controller.dto.request.RacingGameInfoRequest;
import racing.web.controller.dto.request.validator.RacingGameInfoRequestValidator;
import racing.web.controller.dto.request.validator.RequestValidator;
import racing.web.controller.dto.response.RacingCarStateResponse;
import racing.web.controller.dto.response.RacingGameResultResponse;
import racing.web.controller.dto.response.RacingGameSaveResponse;
import racing.web.service.RacingGameService;

@RestController
public class RacingController {

    private final RacingGameService racingGameService;

    public RacingController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameSaveResponse> playGame(@RequestBody RacingGameInfoRequest request) {
        RequestValidator<RacingGameInfoRequest> requestValidator = new RacingGameInfoRequestValidator();
        requestValidator.validate(request);

        Long gameId = racingGameService.playNewGame(request.getCount(), request.getNames());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RacingGameSaveResponse(gameId));
    }

    @GetMapping("/result/{gameId}")
    public ResponseEntity<RacingGameResultResponse> getSingleGameResult(
            @PathVariable(value = "gameId") String gameIdRequest) {
        Long gameId = Long.valueOf(gameIdRequest);

        RacingCarGame racingCarGame = racingGameService.getResultById(gameId);

        return ResponseEntity.ok(toRacingGameResultResponse(racingCarGame));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResultResponse>> getAllResults() {
        List<RacingCarGame> allResults = racingGameService.getAllResults();

        List<RacingGameResultResponse> allResponseResults = allResults.stream()
                .map(this::toRacingGameResultResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allResponseResults);
    }

    private RacingGameResultResponse toRacingGameResultResponse(RacingCarGame racingCarGame) {
        Cars gameCars = racingCarGame.getCars();
        List<RacingCarStateResponse> racingCarsState = gameCars.getCars().stream()
                .map(car -> new RacingCarStateResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());

        String winners = racingGameService.filterWinnersToCarNames(racingCarGame);
        return new RacingGameResultResponse(winners, racingCarsState);
    }

}
