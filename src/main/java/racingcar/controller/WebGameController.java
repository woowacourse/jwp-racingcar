package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GamePlayRequest;
import racingcar.controller.dto.GameResultResponse;
import racingcar.domain.Game;
import racingcar.exception.GameException;
import racingcar.service.GameService;
import racingcar.service.dto.GameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebGameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public WebGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponse> playGame(@RequestBody final GamePlayRequest gamePlayRequest) {
        final List<String> names = List.of(gamePlayRequest.getNames().split(CAR_NAME_SEPARATOR));
        final int playCount = gamePlayRequest.getCount();

        final Game game = gameService.createGameWith(trim(names), playCount);
        final GameResult gameResult = gameService.play(game);

        return ResponseEntity.ok(new GameResultResponse(gameResult.getWinners(), gameResult.getRacingCars()));
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResultResponse>> getAllGameResults() {
        final List<GameResultResponse> responseBody = new ArrayList<>();

        final List<Integer> allPlayedGameIds = gameService.findAllPlayedGameIds();
        for (final int gameId : allPlayedGameIds) {
            final GameResult gameResult = gameService.getGameResult(gameId);
            responseBody.add(new GameResultResponse(gameResult.getWinners(), gameResult.getRacingCars()));
        }

        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(GameException.class)
    private ResponseEntity<String> userInputExceptionHandler(final GameException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
