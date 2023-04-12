package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.PlayRequest;
import racingcar.controller.dto.ResultResponse;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.service.GameService;

@RestController
public class GameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    // 매핑(직렬화)을 누가 해주는지 공부하면 좋을 것 같다.
    public ResponseEntity<ResultResponse> playGame(@RequestBody PlayRequest playRequest) {
        final List<String> names = List.of(playRequest.getNames().split(CAR_NAME_SEPARATOR));
        final int playCount = playRequest.getCount();

        final Game game = gameService.createGameWith(trim(names), playCount);
        gameService.play(game);

        List<String> winnerNames = getNamesOf(game.findWinners());
        List<Car> finishedCars = game.getCars();

        return ResponseEntity.ok(new ResultResponse(winnerNames, finishedCars));
    }

    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
