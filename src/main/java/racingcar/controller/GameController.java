package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.SinglePlayRequest;
import racingcar.domain.Game;
import racingcar.exception.GameException;
import racingcar.service.GameService;
import racingcar.service.dto.SingleGameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<SingleGameResult> playGame(@RequestBody SinglePlayRequest singlePlayRequest) {
        final List<String> names = List.of(singlePlayRequest.getNames().split(CAR_NAME_SEPARATOR));
        final int playCount = singlePlayRequest.getCount();

        final Game game = gameService.createGameWith(trim(names), playCount);
        final int gameId = gameService.play(game);

        return ResponseEntity.ok(gameService.findResult(gameId));
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<SingleGameResult>> getAllGameResults() {
        final List<SingleGameResult> responseBody = new ArrayList<>();

        final List<Integer> allPlayedGameIds = gameService.findAllPlayedGameIds();
        for (final int gameId : allPlayedGameIds) {
            responseBody.add(gameService.findResult(gameId));
        }

        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(GameException.class)
    private ResponseEntity<String> userInputExceptionHandler(final GameException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
