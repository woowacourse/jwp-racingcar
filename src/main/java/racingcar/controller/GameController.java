package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.PlayRequest;
import racingcar.exception.GameException;
import racingcar.service.GameService;
import racingcar.service.dto.ResultDto;

@RestController
public class GameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    // TODO: 매핑(직렬화/역직렬화)을 누가 해주는지 공부하면 좋을 것 같다.
    public ResponseEntity<ResultDto> playGame(@RequestBody PlayRequest playRequest) {
        final List<String> names = List.of(playRequest.getNames().split(CAR_NAME_SEPARATOR));
        final int playCount = playRequest.getCount();

        ResultDto resultDto = gameService.playWith(trim(names), playCount);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultDto>> showGames() {
        List<ResultDto> results = gameService.getAllResults();
        return ResponseEntity.ok(results);
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(GameException.class)
    private ResponseEntity<String> userInputExceptionHandler(final GameException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
